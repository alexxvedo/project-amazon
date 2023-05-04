package baseDatos;

import aplicacion.EmpresaVendedora;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con las empresas vendedoras
public class DAOEmpresasVendedoras extends AbstractDAO {

    public DAOEmpresasVendedoras(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion para obtener el listado de todos las empresas vendedoras que trabajan con nosotros
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras() {

        // Inicializamos el array
        ArrayList<EmpresaVendedora> empresas = new ArrayList<>();

        // Variables que emplearemos para la conexion
        Connection con;
        PreparedStatement stmEmpresasVendedora = null;
        ResultSet rsEmpresaVendedora;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            stmEmpresasVendedora = con.prepareStatement("select * from empresasVendedoras");

            // Lanzamos la consulta indicada
            rsEmpresaVendedora = stmEmpresasVendedora.executeQuery();

            // Iteramos por cada uno de los registros que nos devuelve la base de datos
            while (rsEmpresaVendedora.next()) {

                // Creamos la clase adecuada almacenandola en el array
                EmpresaVendedora empresa = new EmpresaVendedora(rsEmpresaVendedora.getInt("id"), rsEmpresaVendedora.getString("nombre"), rsEmpresaVendedora.getDate("fechaAsociacion"));
                empresas.add(empresa);
                System.out.println(empresa.toString());

            }

            // Gestionamos las posibles excepciones
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmEmpresasVendedora.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return empresas;

    }

}
