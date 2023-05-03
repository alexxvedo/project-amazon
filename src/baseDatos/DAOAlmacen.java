package baseDatos;

import aplicacion.Almacen;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;

public class DAOAlmacen extends AbstractDAO {

    public DAOAlmacen(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Almacen> obtenerAlmacenes() {

        ArrayList<Almacen> almacenes = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlmacenes = null;
        ResultSet rsAlmacenes;

        con = this.getConexion();

        try {

            stmAlmacenes = con.prepareStatement("select * from almacenes");

            rsAlmacenes = stmAlmacenes.executeQuery();

            while (rsAlmacenes.next()) {

                Almacen almacen = new Almacen(rsAlmacenes.getInt("id"), rsAlmacenes.getString("calle"), rsAlmacenes.getInt("numero"), rsAlmacenes.getString("ciudad"), rsAlmacenes.getInt("codigoPostal"));
                almacenes.add(almacen);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmAlmacenes.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return almacenes;

    }

    public int eliminarAlmacen(Almacen almacenEliminar, Almacen almacenDestino) {

        int res = 0;
        Connection con;
        PreparedStatement stmAlmacenEliminar = null;
        PreparedStatement stmProductos = null;

        con = super.getConexion();

        try {

            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            con.setAutoCommit(false);

            stmProductos = con.prepareStatement("update productos set idAlmacen = ? where idAlmacen = ?");
            stmProductos.setInt(1, almacenDestino.getId());
            stmProductos.setInt(2, almacenEliminar.getId());
            stmProductos.executeUpdate();

            stmAlmacenEliminar = con.prepareStatement("delete from almacenes where id = ?");
            stmAlmacenEliminar.setInt(1, almacenEliminar.getId());
            stmAlmacenEliminar.executeUpdate();

            con.commit();

            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(true);

            res = 1;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmAlmacenEliminar.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
