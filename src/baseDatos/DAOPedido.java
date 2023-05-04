package baseDatos;

import aplicacion.Cliente;
import aplicacion.Direccion;
import aplicacion.Distribuidor;
import aplicacion.MetodoPago;
import aplicacion.Pedido;
import aplicacion.Producto;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con pedidos
public class DAOPedido extends AbstractDAO {

    // Nombre del prodcuto prime
    private String primeName = "Amazon Prime";

    public DAOPedido(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion para obtener el listado de todos los pedidos de un cliente
    public ArrayList<Pedido> obtenerPedidos(Cliente c) {

        // Inicializamos el array
        ArrayList<Pedido> pedidos = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmPedidos = null;
        ResultSet rsPedidos;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos el nivel de aislamiento, desactivamos el autocommit
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmPedidos = con.prepareStatement("select * from pedidos where idCliente = ?");
            stmPedidos.setInt(1, c.getId());

            // Ejecutamos la consulta
            rsPedidos = stmPedidos.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsPedidos.next()) {

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Obtemos el metodo de pago empleado empleado en el pedido
                PreparedStatement stmTarjeta = con.prepareStatement("select * from metodosPago where numeroTarjeta = ?");
                stmTarjeta.setLong(1, rsPedidos.getLong("numTarjeta"));

                // Ejecutamos la consulta
                ResultSet rsTarjeta = stmTarjeta.executeQuery();

                MetodoPago tarjeta = null;

                // Comprobamos si tenemos resultado
                if (rsTarjeta.next()) {

                    // Creamos la clase adecuada
                    tarjeta = new MetodoPago(rsTarjeta.getLong("numeroTarjeta"), c, rsTarjeta.getBoolean("activa"), rsTarjeta.getBoolean("preferida"));

                }

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Obtenemos la direccion de envio del pedido
                PreparedStatement stmDireccion = con.prepareStatement("select * from direcciones where id = ?");
                stmDireccion.setInt(1, rsPedidos.getInt("idDireccion"));

                // Ejecutamos la consulta
                ResultSet rsDireccion = stmDireccion.executeQuery();

                Direccion direccion = null;

                // Comprobamos si tenemos resultado
                if (rsDireccion.next()) {

                    // Creamos la clase adecuada
                    direccion = new Direccion(rsDireccion.getInt("id"), c, rsDireccion.getString("calle"), rsDireccion.getInt("numero"), rsDireccion.getString("ciudad"), rsDireccion.getInt("codigopostal"), rsDireccion.getBoolean("preferida"));

                }

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Obtenemos el distribuidor que se encargo de realizar el envio
                PreparedStatement stmDistribuidor = con.prepareStatement("select * from distribuidores where id = ?");
                stmDistribuidor.setInt(1, rsPedidos.getInt("idDistribuidor"));

                // Ejecutamos la consulta
                ResultSet rsDistribuidor = stmDistribuidor.executeQuery();

                Distribuidor distribuidor = null;

                // Comprobamos si tenemos resultado
                if (rsDistribuidor.next()) {

                    // Creamos la clase adecuada
                    distribuidor = new Distribuidor(rsDistribuidor.getInt("id"), rsDistribuidor.getString("nombre"), rsDistribuidor.getInt("telefono"), rsDistribuidor.getFloat("costeEnvio"));

                }

                // Creamos la clase adecuada de pedido y la metemos al array
                Pedido pedido = new Pedido(rsPedidos.getInt("id"), rsPedidos.getDate("fechaPedido"), rsPedidos.getDate("fechaSalida"), rsPedidos.getDate("fechaLlegada"), rsPedidos.getBoolean("completado"), rsPedidos.getFloat("precioTotal"), tarjeta, direccion, c, distribuidor);
                pedidos.add(pedido);

            }

            // Comprometemos los cambios
            con.commit();

            // Devolvemos los valores por defecto a la conexion
            con.setAutoCommit(true);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmPedidos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return pedidos;

    }

    // Funcion que nos permite crear un nuevo pedido
    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean force) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmPedido = null;
        PreparedStatement stmIdPedido = null;
        PreparedStatement stmContener = null;
        PreparedStatement stmProducto = null;
        PreparedStatement stmCliente = null;

        ResultSet rsIdPedido;

        // Obtenemos la conexion, empleando singletone
        con = super.getConexion();

        try {

            // Inilizamos el precio final del pedido
            float precioTotal = 0;

            // Comprobamos que todos los productos tengan la cantidad correcta y ningun exceda el maximo disponible
            for (Map.Entry<Producto, Integer> set : p.entrySet()) {

                if (set.getValue() > set.getKey().getExistencias()) {

                    throw new Exception("Cantidad mayor que existencias");

                }

                // Actualizamos el precio total
                precioTotal += set.getKey().getPrecio() * set.getValue();

            }

            // En caso de que no sea un servicio o no tenga prime sumamos el gasto de envio marcado por el distribuidor
            if (d != null && !c.isPrime()) {
                precioTotal += dist.getCosteEnvio();
            }

            // Indicamos el nivel de aislamiento, desactivamos el autocommit
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            // Empezar creando el pedido
            stmPedido = con.prepareStatement("insert into pedidos values (default, default, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmPedido.setFloat(4, precioTotal);
            stmPedido.setLong(5, m.getNumeroTarjeta());
            stmPedido.setInt(7, c.getId());

            // En caso de que se trate de un servicio los tiempo de emvio y recepcion son inmediatos
            // En caso contrario seran notificados por el distribuidor
            if (force) {
                Date fechaActual = new Date();
                stmPedido.setDate(1, new java.sql.Date(fechaActual.getTime()));
                stmPedido.setDate(2, new java.sql.Date(fechaActual.getTime()));
                stmPedido.setBoolean(3, true);
            } else {
                stmPedido.setNull(1, java.sql.Types.DATE);
                stmPedido.setNull(2, java.sql.Types.DATE);
                stmPedido.setBoolean(3, false);
            }

            // Comprobamos si tenemos direccion
            if (d != null) {
                stmPedido.setInt(6, d.getId());
            } else {
                stmPedido.setNull(6, java.sql.Types.INTEGER);
            }

            // Comprobamos si tenemos distribuidor
            if (dist != null) {
                stmPedido.setInt(8, dist.getId());
            } else {
                stmPedido.setNull(8, java.sql.Types.INTEGER);
            }

            // Ejecutamos la consulta
            stmPedido.executeUpdate();

            // Indicamos cual es la consulta que queremos lanzar
            // Obtenemo el id del ultimo pedido que acabamos de crear
            stmIdPedido = con.prepareStatement("select currval('pedidos_id_seq') as idPedido");

            // Ejecutamos la consulta
            rsIdPedido = stmIdPedido.executeQuery();

            rsIdPedido.next();

            // Recuperamos el id del pedido
            int idPedido = rsIdPedido.getInt("idPedido");

            // Relacionamos todos los productos que se compraron con el pedido actualizando sus existencias
            for (Map.Entry<Producto, Integer> set : p.entrySet()) {

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Vinculamos el producto al pedido
                stmContener = con.prepareStatement("insert into contener values (?, ?, ?)");
                stmContener.setInt(1, idPedido);
                stmContener.setInt(2, set.getKey().getId());
                stmContener.setInt(3, set.getValue());

                // Ejecutamos la consulta
                stmContener.executeUpdate();

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Actualizamos las existencias
                stmProducto = con.prepareStatement("update productos set existencias = existencias - ? where id = ?");
                stmProducto.setInt(1, set.getValue());
                stmProducto.setInt(2, set.getKey().getId());

                // Ejecutamos la consulta
                stmProducto.executeUpdate();

            }

            // Comprometemos los cambios
            con.commit();

            // Devolvemos los valores por defecto a la conexion
            con.setAutoCommit(true);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmPedido.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;

    }

}
