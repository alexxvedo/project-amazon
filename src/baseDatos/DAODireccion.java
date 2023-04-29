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

            stmDirecciones = con.prepareStatement("select d.* from direcciones as d inner join residir as r on r.idDireccion = d.id and r.idCliente = ?");
            stmDirecciones.setInt(1, c.getId());
            rsDirecciones = stmDirecciones.executeQuery();

            while (rsDirecciones.next()) {

                Direccion direccion = new Direccion(rsDirecciones.getInt("id"), rsDirecciones.getString("calle"), rsDirecciones.getInt("numero"), rsDirecciones.getString("ciudad"), rsDirecciones.getInt("codigopostal"), rsDirecciones.getBoolean("preferida"));
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

    public int crearDireccion(Cliente c, Direccion d) {

        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;
        PreparedStatement stmIdDireccion = null;
        PreparedStatement stmResidir = null;

        ResultSet rsIdDireccion;

        con = super.getConexion();

        try {

            stmDireccion = con.prepareStatement("insert into direcciones values (default, ?, ?, ?, ?, ?)");
            stmDireccion.setString(1, d.getCalle());
            stmDireccion.setInt(2, d.getNumero());
            stmDireccion.setString(3, d.getCiudad());
            stmDireccion.setInt(4, d.getCodigoPostal());
            stmDireccion.setBoolean(5, d.isPreferida());
            stmDireccion.executeUpdate();

            stmIdDireccion = con.prepareStatement("select currval('direcciones_id_seq') as idDireccion");
            rsIdDireccion = stmIdDireccion.executeQuery();
            rsIdDireccion.next();

            int idDireccion = rsIdDireccion.getInt("idDireccion");

            stmResidir = con.prepareStatement("insert into residir values (?, ?)");
            stmResidir.setInt(1, c.getId());
            stmResidir.setInt(2, idDireccion);
            stmResidir.executeUpdate();

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
