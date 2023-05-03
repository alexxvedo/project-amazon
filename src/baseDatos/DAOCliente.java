package baseDatos;

import aplicacion.Cliente;
import java.awt.Color;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return resultado;

    }
    
    public int crearCliente(String nombre, String email, String password, String fechaNacimiento, boolean prime, int telefono){
        int res = 0;
        Connection con;
        PreparedStatement stmCliente = null;

        con = super.getConexion();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        java.sql.Date fecha = null;
        try {
            parsed = format.parse(fechaNacimiento);
             fecha = new java.sql.Date(parsed.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        try {

            stmCliente = con.prepareStatement("insert into clientes values (default, ?, ?, ?, ?, ?, ?)");
            stmCliente.setString(1, nombre);
            stmCliente.setInt(2, telefono);
            stmCliente.setDate(3, fecha);
            stmCliente.setBoolean(4, prime);
            stmCliente.setString(5, email);
            stmCliente.setString(6, password);
            stmCliente.executeUpdate();

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }
        }

        return res;
    }

    public int actualizarCliente(Cliente c) {

        int res = 0;
        Connection con;
        PreparedStatement stmCliente = null;

        con = super.getConexion();

        try {

            stmCliente = con.prepareStatement("update clientes set nombre = ?, telefono = ?, fechanacimiento = ?, prime = ?, email = ?, contrasena = ? where id = ?");
            stmCliente.setString(1, c.getNombre());
            stmCliente.setInt(2, c.getTelefono());
            stmCliente.setDate(3, java.sql.Date.valueOf(c.getFechaNacimientoString()));
            stmCliente.setBoolean(4, c.isPrime());
            stmCliente.setString(5, c.getEmail());
            stmCliente.setString(6, c.getContrasena());
            stmCliente.setInt(7, c.getId());
            stmCliente.executeUpdate();

            res = 1;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }
        }

        return res;

    }

    public int eliminarCliente(Cliente c) {

        int res = 0;
        Connection con;
        PreparedStatement stmCliente = null;

        con = super.getConexion();

        try {

            stmCliente = con.prepareStatement("delete from clientes where id = ?");
            stmCliente.setInt(1, c.getId());
            stmCliente.executeUpdate();

            res = 1;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);
            res = 0;

        } finally {

            try {

                stmCliente.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return res;

    }

}
