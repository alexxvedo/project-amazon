package baseDatos;

import aplicacion.Cliente;
import aplicacion.MetodoPago;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMetodoPago extends AbstractDAO {

    public DAOMetodoPago(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {

        ArrayList<MetodoPago> metodosPago = new ArrayList<>();
        Connection con;
        PreparedStatement stmMetodosPago = null;
        ResultSet rsMetodosPago;

        con = this.getConexion();

        try {

            stmMetodosPago = con.prepareStatement("select * from metodosPago where idCliente = ?");
            stmMetodosPago.setInt(1, c.getId());
            rsMetodosPago = stmMetodosPago.executeQuery();

            while (rsMetodosPago.next()) {

                MetodoPago metodoPago = new MetodoPago(rsMetodosPago.getLong("numeroTarjeta"), c, rsMetodosPago.getBoolean("activa"), rsMetodosPago.getBoolean("preferida"));
                metodosPago.add(metodoPago);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmMetodosPago.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return metodosPago;

    }

    public int crearMetodoPago(MetodoPago m) {

        int res = 0;
        Connection con;
        PreparedStatement stmMetodoPago = null;

        con = super.getConexion();

        try {

            stmMetodoPago = con.prepareStatement("insert into metodosPago values (?, ?, ?, ?)");
            stmMetodoPago.setLong(1, m.getNumeroTarjeta());
            stmMetodoPago.setInt(2, m.getCliente().getId());
            stmMetodoPago.setBoolean(3, m.isActiva());
            stmMetodoPago.setBoolean(4, m.isPreferida());
            stmMetodoPago.executeUpdate();

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmMetodoPago.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");
                res = 0;

            }
        }

        return res;

    }

    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {

        int res = 0;
        Connection con;
        PreparedStatement stmDireccion = null;

        con = super.getConexion();

        try {

            stmDireccion = con.prepareStatement("update metodosPago set numeroTarjeta = ?, activa = ?, preferida = ? where numeroTarjeta = ?");
            stmDireccion.setLong(1, newM.getNumeroTarjeta());
            stmDireccion.setBoolean(2, newM.isActiva());
            stmDireccion.setBoolean(3, newM.isPreferida());
            stmDireccion.setLong(4, oldM.getNumeroTarjeta());
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

    public int eliminarMetodoPago(MetodoPago m) {

        int res = 0;
        Connection con;
        PreparedStatement stmMetodoPago = null;

        con = super.getConexion();

        try {

            stmMetodoPago = con.prepareStatement("delete from metodosPago where numerotarjeta = ?");
            stmMetodoPago.setLong(1, m.getNumeroTarjeta());
            stmMetodoPago.executeUpdate();

            res = 1;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmMetodoPago.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
