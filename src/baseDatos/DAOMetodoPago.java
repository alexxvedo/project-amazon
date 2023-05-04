package baseDatos;

import aplicacion.Cliente;
import aplicacion.MetodoPago;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con los metodos de pago
public class DAOMetodoPago extends AbstractDAO {

    public DAOMetodoPago(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion para obtener el listado de todos los metodos de pago disponibles para un cliente
    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {

        // Inicializamos el array
        ArrayList<MetodoPago> metodosPago = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmMetodosPago = null;
        ResultSet rsMetodosPago;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Indicamos los parametros que queremos emplear en la sentencia
            stmMetodosPago = con.prepareStatement("select * from metodosPago where idCliente = ?");
            stmMetodosPago.setInt(1, c.getId());

            // Lanzamos la consulta indicada
            rsMetodosPago = stmMetodosPago.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsMetodosPago.next()) {

                // Creamos la clase adecuada almacenandola en el array
                MetodoPago metodoPago = new MetodoPago(rsMetodosPago.getLong("numeroTarjeta"), c, rsMetodosPago.getBoolean("activa"), rsMetodosPago.getBoolean("preferida"));
                metodosPago.add(metodoPago);

            }

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmMetodosPago.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        // Devolvemos la informacion obtenida
        return metodosPago;

    }

    // Funcion que permite a un cliente crear un nuevo metodo de pago para usar en la aplicacion
    public int crearMetodoPago(MetodoPago m) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmMetodoPago = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmMetodoPago = con.prepareStatement("insert into metodosPago values (?, ?, ?, ?)");
            stmMetodoPago.setLong(1, m.getNumeroTarjeta());
            stmMetodoPago.setInt(2, m.getCliente().getId());
            stmMetodoPago.setBoolean(3, m.isActiva());
            stmMetodoPago.setBoolean(4, m.isPreferida());

            // Ejecutamos la consulta
            stmMetodoPago.executeUpdate();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmMetodoPago.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;

    }

    // Funcion que permite a un cliente actualizar alguno de sus metodos de pago
    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmDireccion = con.prepareStatement("update metodosPago set numeroTarjeta = ?, activa = ?, preferida = ? where numeroTarjeta = ?");
            stmDireccion.setLong(1, newM.getNumeroTarjeta());
            stmDireccion.setBoolean(2, newM.isActiva());
            stmDireccion.setBoolean(3, newM.isPreferida());
            stmDireccion.setLong(4, oldM.getNumeroTarjeta());

            // Ejecutamos la sentencias
            stmDireccion.executeUpdate();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmDireccion.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }
        }

        return res;

    }

    // Funcion para que un cliente pueda eliminar alguno de sus metodos de pago
    public int eliminarMetodoPago(MetodoPago m) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmMetodoPago = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmMetodoPago = con.prepareStatement("delete from metodosPago where numerotarjeta = ?");
            stmMetodoPago.setLong(1, m.getNumeroTarjeta());

            // Ejecutamos la consulta
            stmMetodoPago.executeUpdate();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmMetodoPago.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
