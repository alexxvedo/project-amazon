package baseDatos;

import aplicacion.Almacen;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con almacen
public class DAOAlmacen extends AbstractDAO {

    public DAOAlmacen(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion para obtener el listado de todos los almacenes disponibles
    public ArrayList<Almacen> obtenerAlmacenes() {

        // Inicializamos el array
        ArrayList<Almacen> almacenes = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmAlmacenes = null;
        ResultSet rsAlmacenes;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            stmAlmacenes = con.prepareStatement("select * from almacenes");

            // Lanzamos la consulta indicada
            rsAlmacenes = stmAlmacenes.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsAlmacenes.next()) {

                // Creamos la clase adecuada almacenandola en el array
                Almacen almacen = new Almacen(rsAlmacenes.getInt("id"), rsAlmacenes.getString("calle"), rsAlmacenes.getInt("numero"), rsAlmacenes.getString("ciudad"), rsAlmacenes.getInt("codigoPostal"));
                almacenes.add(almacen);

            }

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmAlmacenes.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        // Devolvemos la informacion obtenida
        return almacenes;

    }

    // Funcion para eliminar un almacen, moviendo los demas productos a otro disponible
    public int eliminarAlmacen(Almacen almacenEliminar, Almacen almacenDestino) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmAlmacenEliminar = null;
        PreparedStatement stmProductos = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos el nivel de aislamiento, desactivamos el autocommit
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            con.setAutoCommit(false);

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            // Cambiamos los productos de almacen
            stmProductos = con.prepareStatement("update productos set idAlmacen = ? where idAlmacen = ?");
            stmProductos.setInt(1, almacenDestino.getId());
            stmProductos.setInt(2, almacenEliminar.getId());

            // Ejecutamos la consulta
            stmProductos.executeUpdate();

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            // Eliminamos el almacen
            stmAlmacenEliminar = con.prepareStatement("delete from almacenes where id = ?");
            stmAlmacenEliminar.setInt(1, almacenEliminar.getId());

            // Ejecutamos la consulta
            stmAlmacenEliminar.executeUpdate();

            // Confirmamos los cambios
            con.commit();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            try {

                con.rollback();

            } catch (SQLException ex) {

                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(ex.getMessage(), Color.RED);

            }

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                // Restauramos los valores por defecto
                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                con.setAutoCommit(true);

                // Cerramos los cursores
                stmAlmacenEliminar.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
