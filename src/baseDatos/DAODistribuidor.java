package baseDatos;

import aplicacion.Distribuidor;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODistribuidor extends AbstractDAO {

    public DAODistribuidor(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Distribuidor> obtenerDistribuidores() {

        ArrayList<Distribuidor> distribuidores = new ArrayList<>();
        Connection con;
        PreparedStatement stmDistribuidores = null;
        ResultSet rsDistribuidores;

        con = this.getConexion();

        try {

            stmDistribuidores = con.prepareStatement("select * from distribuidores");
            rsDistribuidores = stmDistribuidores.executeQuery();

            while (rsDistribuidores.next()) {

                Distribuidor distribuidor = new Distribuidor(rsDistribuidores.getInt("id"), rsDistribuidores.getString("nombre"), rsDistribuidores.getInt("telefono"), rsDistribuidores.getFloat("costeEnvio"));
                distribuidores.add(distribuidor);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmDistribuidores.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return distribuidores;

    }
}
