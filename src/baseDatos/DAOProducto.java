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

public class DAOProducto extends AbstractDAO {

    public DAOProducto(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Producto> obtenerProducts(String nombre) {

        ArrayList<Producto> productos = new ArrayList<>();
        Connection con;
        PreparedStatement stmProductos = null;
        ResultSet rsProductos;

        con = this.getConexion();

        try {

            stmProductos = con.prepareStatement("select * from productos where nombre like ? and existencias != 0");
            stmProductos.setString(1, "%" + nombre + "%");
            rsProductos = stmProductos.executeQuery();

            while (rsProductos.next()) {

                PreparedStatement stmEmpresa = con.prepareStatement("select * from empresasvendedoras where id = ?");
                stmEmpresa.setInt(1, rsProductos.getInt("idEmpresa"));
                ResultSet rsEmpresa = stmEmpresa.executeQuery();

                EmpresaVendedora empresa = null;

                if (rsEmpresa.next()) {
                    empresa = new EmpresaVendedora(rsEmpresa.getInt("id"), rsEmpresa.getString("nombre"), rsEmpresa.getDate("fechaasociacion"));
                }

                PreparedStatement stmAlmacen = con.prepareStatement("select * from almacenes where id = ?");
                stmAlmacen.setInt(1, rsProductos.getInt("idAlmacen"));
                ResultSet rsAlmacen = stmAlmacen.executeQuery();

                Almacen almacen = null;

                if (rsAlmacen.next()) {
                    almacen = new Almacen(rsAlmacen.getInt("id"), rsAlmacen.getString("calle"), rsAlmacen.getInt("numero"), rsAlmacen.getString("ciudad"), rsAlmacen.getInt("codigoPostal"));
                }

                Producto producto = new Producto(rsProductos.getInt("id"), empresa, almacen, rsProductos.getString("nombre"), rsProductos.getString("descripcion"), rsProductos.getFloat("precio"), rsProductos.getInt("existencias"));
                productos.add(producto);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmProductos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return productos;

    }

    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, String nombre, String descripcion, float precio, int existencias) {

        Connection con;
        PreparedStatement stmProductos = null;

        int res = 0;

        con = this.getConexion();
        try {

            stmProductos = con.prepareStatement("insert into productos values (default, ?, ?, ?, ?, ?, ?)");
            stmProductos.setInt(1, selectedEmpresa.getId());
            stmProductos.setInt(2, selectedAlmacen.getId());
            stmProductos.setString(3, nombre);
            stmProductos.setString(4, descripcion);
            stmProductos.setFloat(5, precio);
            stmProductos.setInt(6, existencias);
            stmProductos.executeUpdate();

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmProductos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;
    }

}
