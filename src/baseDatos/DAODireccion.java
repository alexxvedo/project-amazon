package baseDatos;

import aplicacion.Cliente;
import aplicacion.Direccion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODireccion extends AbstractDAO {

    public DAODireccion(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {

        ArrayList<Direccion> direcciones = new ArrayList<>();
        Connection con;
        PreparedStatement stmDirecciones = null;
        ResultSet rsDirecciones;

        con = this.getConexion();

        try {

            stmDirecciones = con.prepareStatement("select * from direcciones where idCliente = ?");
            stmDirecciones.setInt(1, c.getId());
            rsDirecciones = stmDirecciones.executeQuery();

            while (rsDirecciones.next()) {

                Direccion direccion = new Direccion(rsDirecciones.getInt("id"), c, rsDirecciones.getString("calle"), rsDirecciones.getInt("numero"), rsDirecciones.getString("ciudad"), rsDirecciones.getInt("codigopostal"), rsDirecciones.getBoolean("preferida"));
                direcciones.add(direccion);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmDirecciones.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return direcciones;

    }

    public int crearDireccion(Direccion d) {

        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        con = super.getConexion();

        try {

            stmDireccion = con.prepareStatement("insert into direcciones values (default, ?, ?, ?, ?, ?, ?)");
            stmDireccion.setInt(1, d.getCliente().getId());
            stmDireccion.setString(2, d.getCalle());
            stmDireccion.setInt(3, d.getNumero());
            stmDireccion.setString(4, d.getCiudad());
            stmDireccion.setInt(5, d.getCodigoPostal());
            stmDireccion.setBoolean(6, d.isPreferida());
            stmDireccion.executeUpdate();

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmDireccion.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;

    }

    public int actualizarDireccion(Direccion d) {

        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        con = super.getConexion();

        try {

            stmDireccion = con.prepareStatement("update direcciones set calle = ?, numero = ?, ciudad = ?, codigoPostal = ?, preferida = ? where id = ?");
            stmDireccion.setString(1, d.getCalle());
            stmDireccion.setInt(2, d.getNumero());
            stmDireccion.setString(3, d.getCiudad());
            stmDireccion.setInt(4, d.getCodigoPostal());
            stmDireccion.setBoolean(5, d.isPreferida());
            stmDireccion.setInt(6, d.getId());
            stmDireccion.executeUpdate();

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmDireccion.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }
        }

        return res;

    }

    public int eliminarDireccion(Direccion d) {

        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        con = super.getConexion();

        try {

            stmDireccion = con.prepareStatement("delete from direcciones where id = ?");
            stmDireccion.setInt(1, d.getId());
            stmDireccion.executeUpdate();

            res = 1;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmDireccion.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
