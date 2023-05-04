package baseDatos;

import aplicacion.Cliente;
import aplicacion.Direccion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con las direcciones de envio
public class DAODireccion extends AbstractDAO {

    public DAODireccion(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion para obtener el listado de todos las direcciones de envio de un cliente
    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {

        // Inicializamos el array
        ArrayList<Direccion> direcciones = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmDirecciones = null;
        ResultSet rsDirecciones;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            stmDirecciones = con.prepareStatement("select * from direcciones where idCliente = ?");
            stmDirecciones.setInt(1, c.getId());

            // Lanzamos la consulta indicada
            rsDirecciones = stmDirecciones.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsDirecciones.next()) {

                // Creamos la clase adecuada almacenandola en el array
                Direccion direccion = new Direccion(rsDirecciones.getInt("id"), c, rsDirecciones.getString("calle"), rsDirecciones.getInt("numero"), rsDirecciones.getString("ciudad"), rsDirecciones.getInt("codigopostal"), rsDirecciones.getBoolean("preferida"));
                direcciones.add(direccion);

            }

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmDirecciones.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return direcciones;

    }

    // Funcion que nos permite crear un nueva direccion de envio
    public int crearDireccion(Direccion d) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmDireccion = con.prepareStatement("insert into direcciones values (default, ?, ?, ?, ?, ?, ?)");
            stmDireccion.setInt(1, d.getCliente().getId());
            stmDireccion.setString(2, d.getCalle());
            stmDireccion.setInt(3, d.getNumero());
            stmDireccion.setString(4, d.getCiudad());
            stmDireccion.setInt(5, d.getCodigoPostal());
            stmDireccion.setBoolean(6, d.isPreferida());

            // Ejecutamos la consulta
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
                res = 0;

            }
        }

        return res;

    }

    // Funcion que nos permite actualiza la direccion de envio de un cliente 
    public int actualizarDireccion(Direccion d) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmDireccion = con.prepareStatement("update direcciones set calle = ?, numero = ?, ciudad = ?, codigoPostal = ?, preferida = ? where id = ?");
            stmDireccion.setString(1, d.getCalle());
            stmDireccion.setInt(2, d.getNumero());
            stmDireccion.setString(3, d.getCiudad());
            stmDireccion.setInt(4, d.getCodigoPostal());
            stmDireccion.setBoolean(5, d.isPreferida());
            stmDireccion.setInt(6, d.getId());

            // Ejecutamos la consulta
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

    public int eliminarDireccion(Direccion d) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmDireccion = con.prepareStatement("delete from direcciones where id = ?");
            stmDireccion.setInt(1, d.getId());

            // Ejecutamos la consulta
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

}
