package baseDatos;

import aplicacion.Cliente;
import java.awt.Color;
import java.sql.*;

// Clase de acceso a datos de tipo almacen, encargada de todas las acciones contra la base de datos relacionadas con clientes
public class DAOCliente extends AbstractDAO {

    public DAOCliente(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    // Funcion que nos permite validar las credenciales de un cliente
    public Cliente validarUsuario(String email, String password) {

        // Variables que emplearemos para la conexion
        Cliente resultado = null;
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsCliente;

        // Obtenemos la conexion, empleando singletone
        con = this.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmCliente = con.prepareStatement("select * from clientes where email = ? and contrasena = ?");
            stmCliente.setString(1, email);
            stmCliente.setString(2, password);

            // Ejecutamos la consulta
            rsCliente = stmCliente.executeQuery();

            // Comprobamos que los datos proporcionados coinciden con los datos de un cliente
            if (rsCliente.next()) {

                // Creamos el objeto cliente
                resultado = new Cliente(rsCliente.getInt("id"), rsCliente.getString("nombre"), rsCliente.getInt("telefono"), rsCliente.getDate("fechaNacimiento"), rsCliente.getBoolean("prime"), rsCliente.getString("email"), rsCliente.getString("contrasena"));

            }

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error de BBDD", Color.RED);

        } finally {

            try {

                // Cerramos los cursores
                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return resultado;

    }

    // Funcion que nos permite que un cliente se registre en nuestro sistema
    public int crearCliente(Cliente c) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmCliente = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmCliente = con.prepareStatement("insert into clientes values (default, ?, ?, ?, ?, ?, ?)");
            stmCliente.setString(1, c.getNombre());
            stmCliente.setInt(2, c.getTelefono());
            stmCliente.setDate(3, java.sql.Date.valueOf(c.getFechaNacimientoString()));
            stmCliente.setBoolean(4, c.isPrime());
            stmCliente.setString(5, c.getEmail());
            stmCliente.setString(6, c.getContrasena());

            // Ejecutamos la consulta
            stmCliente.executeUpdate();

            // Indicamos que todo funciono correctamente
            res = 1;

            // Comprobamos si ocurrio una excepcion de SQL
        } catch (SQLException e) {

            // Obtenemos el mensaje de error
            String text = e.getMessage();

            // Si es un error de duplicados actualizamos el texto para ser mas directos con el cliente
            if (Integer.parseInt(e.getSQLState()) == 23505) {
                text = "Error email duplicado";
            }

            // Mostramos el fallo
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(text, Color.RED);

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error de BBDD", Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;
    }

    // Funcion que permite a un cliente actualizar sus datos de perfil
    public int actualizarCliente(Cliente c) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmCliente = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmCliente = con.prepareStatement("update clientes set nombre = ?, telefono = ?, fechanacimiento = ?, prime = ?, email = ?, contrasena = ? where id = ?");
            stmCliente.setString(1, c.getNombre());
            stmCliente.setInt(2, c.getTelefono());
            stmCliente.setDate(3, java.sql.Date.valueOf(c.getFechaNacimientoString()));
            stmCliente.setBoolean(4, c.isPrime());
            stmCliente.setString(5, c.getEmail());
            stmCliente.setString(6, c.getContrasena());
            stmCliente.setInt(7, c.getId());

            // Ejecutamos la consulta
            stmCliente.executeUpdate();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error de BBDD", Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }
        }

        return res;

    }

    // Funcion para eliminar un cliente de la base de datos
    public int eliminarCliente(Cliente c) {

        // Variables que emplearemos para la conexion
        int res = 0;
        Connection con;
        PreparedStatement stmCliente = null;

        // Obtenemos la conexion
        con = super.getConexion();

        try {

            // Indicamos cual es la consulta que queremos lanzar
            // Pasando los parametros que queremos que emplee
            stmCliente = con.prepareStatement("delete from clientes where id = ?");
            stmCliente.setInt(1, c.getId());
            stmCliente.executeUpdate();

            res = 1;

            // Comprobamos si se produjo algun error
        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error de BBDD", Color.RED);
            res = 0;

        } finally {

            try {

                // Cerramos los cursores
                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
