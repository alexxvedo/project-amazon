package baseDatos;

import aplicacion.Distribuidor;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con distribuidores
public class DAODistribuidor extends AbstractDAO {

    public DAODistribuidor(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion para obtener el listado de todos los distribuidores disponibles
    public ArrayList<Distribuidor> obtenerDistribuidores() {

        // Inicializamos el array
        ArrayList<Distribuidor> distribuidores = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmDistribuidores = null;
        ResultSet rsDistribuidores;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            stmDistribuidores = con.prepareStatement("select * from distribuidores");

            // Lanzamos la consulta indicada
            rsDistribuidores = stmDistribuidores.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsDistribuidores.next()) {

                // Creamos la clase adecuada almacenandola en el array
                Distribuidor distribuidor = new Distribuidor(rsDistribuidores.getInt("id"), rsDistribuidores.getString("nombre"), rsDistribuidores.getInt("telefono"), rsDistribuidores.getFloat("costeEnvio"));
                distribuidores.add(distribuidor);

            }

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmDistribuidores.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return distribuidores;

    }

}
