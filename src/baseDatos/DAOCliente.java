package baseDatos;

import aplicacion.Cliente;
import java.sql.*;

public class DAOCliente extends AbstractDAO {

    public DAOCliente(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Cliente validarUsuario(String email, String password) {

        Cliente resultado = null;
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsCliente;

        con = this.getConexion();

        try {

            stmCliente = con.prepareStatement("select * from clientes where email = ? and contrasena = ?");
            stmCliente.setString(1, email);
            stmCliente.setString(2, password);
            rsCliente = stmCliente.executeQuery();

            if (rsCliente.next()) {

                resultado = new Cliente(rsCliente.getInt("id"), rsCliente.getString("nombre"), rsCliente.getInt("telefono"), rsCliente.getDate("fechaNacimiento"), rsCliente.getBoolean("prime"), rsCliente.getString("email"), rsCliente.getString("contrasena"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());

        } finally {

            try {

                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return resultado;

    }

}
