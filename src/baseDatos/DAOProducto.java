package baseDatos;

import aplicacion.Almacen;
import aplicacion.EmpresaVendedora;
import aplicacion.Producto;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con productos
public class DAOProducto extends AbstractDAO {

    public DAOProducto(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion que nos permite obtener un listado con todos los productos a la venta
    public ArrayList<Producto> obtenerProducts(String nombre) {

        // Inicializamos el array
        ArrayList<Producto> productos = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmProductos = null;
        ResultSet rsProductos;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmProductos = con.prepareStatement("select * from productos where nombre like ? and existencias != 0");
            stmProductos.setString(1, "%" + nombre + "%");

            // Ejecutamos la consulta
            rsProductos = stmProductos.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsProductos.next()) {

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Obtenemos los datos completos de la empresa que lo vende
                PreparedStatement stmEmpresa = con.prepareStatement("select * from empresasvendedoras where id = ?");
                stmEmpresa.setInt(1, rsProductos.getInt("idEmpresa"));

                // Ejecutamos la consulta
                ResultSet rsEmpresa = stmEmpresa.executeQuery();

                EmpresaVendedora empresa = null;

                // Si obtenemos resultados
                if (rsEmpresa.next()) {

                    // Creamos la clase adecuada almacenandola en el array
                    empresa = new EmpresaVendedora(rsEmpresa.getInt("id"), rsEmpresa.getString("nombre"), rsEmpresa.getDate("fechaasociacion"));

                }

                // Indicamos cual es la consulta que queremos lanzar
                // Pasando los parametros que queremos que emplee
                // Obtenemos los datos del almacen donde esta
                PreparedStatement stmAlmacen = con.prepareStatement("select * from almacenes where id = ?");
                stmAlmacen.setInt(1, rsProductos.getInt("idAlmacen"));

                // Ejecutamos la sentencia
                ResultSet rsAlmacen = stmAlmacen.executeQuery();

                Almacen almacen = null;

                // Si obtenemos resultados
                if (rsAlmacen.next()) {

                    // Creamos la clase adecuada almacenandola en el array
                    almacen = new Almacen(rsAlmacen.getInt("id"), rsAlmacen.getString("calle"), rsAlmacen.getInt("numero"), rsAlmacen.getString("ciudad"), rsAlmacen.getInt("codigoPostal"));

                }

                // Creamos la clase adecuada almacenandola en el array
                Producto producto = new Producto(rsProductos.getInt("id"), empresa, almacen, rsProductos.getString("nombre"), rsProductos.getString("descripcion"), rsProductos.getFloat("precio"), rsProductos.getInt("existencias"));
                productos.add(producto);

            }

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmProductos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return productos;

    }

    // Funcion que permite a los administradores crear nuevos productos
    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, Producto p) {

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmProductos = null;

        int res = 0;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmProductos = con.prepareStatement("insert into productos values (default, ?, ?, ?, ?, ?, ?)");
            stmProductos.setInt(1, selectedEmpresa.getId());
            stmProductos.setInt(2, selectedAlmacen.getId());
            stmProductos.setString(3, p.getNombre());
            stmProductos.setString(4, p.getDescripcion());
            stmProductos.setFloat(5, p.getPrecio());
            stmProductos.setInt(6, p.getExistencias());

            // Ejecutamos la consulta
            stmProductos.executeUpdate();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmProductos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;
    }

}
