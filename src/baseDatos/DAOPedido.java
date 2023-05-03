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

public class DAOPedido extends AbstractDAO {

    private String primeName = "Amazon Prime";

    public DAOPedido(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {

        ArrayList<Pedido> pedidos = new ArrayList<>();
        Connection con;
        PreparedStatement stmPedidos = null;
        ResultSet rsPedidos;

        con = this.getConexion();

        try {

            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            stmPedidos = con.prepareStatement("select * from pedidos where idCliente = ?");
            stmPedidos.setInt(1, c.getId());
            rsPedidos = stmPedidos.executeQuery();

            while (rsPedidos.next()) {

                PreparedStatement stmTarjeta = con.prepareStatement("select * from metodosPago where numeroTarjeta = ?");
                stmTarjeta.setLong(1, rsPedidos.getLong("numTarjeta"));
                ResultSet rsTarjeta = stmTarjeta.executeQuery();

                MetodoPago tarjeta = null;

                if (rsTarjeta.next()) {
                    tarjeta = new MetodoPago(rsTarjeta.getLong("numeroTarjeta"), c, rsTarjeta.getBoolean("activa"), rsTarjeta.getBoolean("preferida"));
                }

                PreparedStatement stmDireccion = con.prepareStatement("select * from direcciones where id = ?");
                stmDireccion.setInt(1, rsPedidos.getInt("idDireccion"));
                ResultSet rsDireccion = stmDireccion.executeQuery();

                Direccion direccion = null;

                if (rsDireccion.next()) {
                    direccion = new Direccion(rsDireccion.getInt("id"), c, rsDireccion.getString("calle"), rsDireccion.getInt("numero"), rsDireccion.getString("ciudad"), rsDireccion.getInt("codigopostal"), rsDireccion.getBoolean("preferida"));
                }

                PreparedStatement stmDistribuidor = con.prepareStatement("select * from distribuidores where id = ?");
                stmDistribuidor.setInt(1, rsPedidos.getInt("idDistribuidor"));
                ResultSet rsDistribuidor = stmDistribuidor.executeQuery();

                Distribuidor distribuidor = null;

                if (rsDistribuidor.next()) {
                    distribuidor = new Distribuidor(rsDistribuidor.getInt("id"), rsDistribuidor.getString("nombre"), rsDistribuidor.getInt("telefono"), rsDistribuidor.getFloat("costeEnvio"));
                }

                Pedido pedido = new Pedido(rsPedidos.getInt("id"), rsPedidos.getDate("fechaPedido"), rsPedidos.getDate("fechaSalida"), rsPedidos.getDate("fechaLlegada"), rsPedidos.getBoolean("completado"), rsPedidos.getFloat("precioTotal"), tarjeta, direccion, c, distribuidor);
                pedidos.add(pedido);

            }

            con.commit();
            con.setAutoCommit(true);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmPedidos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return pedidos;

    }

    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean force) {

        int res = 0;
        Connection con;
        PreparedStatement stmPedido = null;
        PreparedStatement stmIdPedido = null;
        PreparedStatement stmContener = null;
        PreparedStatement stmProducto = null;
        PreparedStatement stmCliente = null;

        ResultSet rsIdPedido;

        con = super.getConexion();

        try {

            float precioTotal = 0;

            for (Map.Entry<Producto, Integer> set : p.entrySet()) {

                if (set.getValue() > set.getKey().getExistencias()) {

                    throw new Exception("Cantidad mayor que existencias");

                }

                precioTotal += set.getKey().getPrecio() * set.getValue();

            }

            if (d != null && !c.isPrime()) {
                precioTotal += dist.getCosteEnvio();
            }

            stmPedido = con.prepareStatement("insert into pedidos values (default, default, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmPedido.setFloat(4, precioTotal);
            stmPedido.setLong(5, m.getNumeroTarjeta());
            stmPedido.setInt(7, c.getId());

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

            if (d != null) {
                stmPedido.setInt(6, d.getId());
            } else {
                stmPedido.setNull(6, java.sql.Types.INTEGER);
            }

            if (dist != null) {
                stmPedido.setInt(8, dist.getId());
            } else {
                stmPedido.setNull(8, java.sql.Types.INTEGER);
            }

            stmPedido.executeUpdate();

            stmIdPedido = con.prepareStatement("select currval('pedidos_id_seq') as idPedido");
            rsIdPedido = stmIdPedido.executeQuery();
            rsIdPedido.next();

            int idPedido = rsIdPedido.getInt("idPedido");

            for (Map.Entry<Producto, Integer> set : p.entrySet()) {

                stmContener = con.prepareStatement("insert into contener values (?, ?, ?)");
                stmContener.setInt(1, idPedido);
                stmContener.setInt(2, set.getKey().getId());
                stmContener.setInt(3, set.getValue());
                stmContener.executeUpdate();

                stmProducto = con.prepareStatement("update productos set existencias = existencias - ? where id = ?");
                stmProducto.setInt(1, set.getValue());
                stmProducto.setInt(2, set.getKey().getId());
                stmProducto.executeUpdate();

            }

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmPedido.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;

    }

}
