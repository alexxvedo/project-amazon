package baseDatos;

import aplicacion.Cliente;
import aplicacion.Pedido;
import aplicacion.Producto;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class FachadaBaseDatos {

    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOCliente daoClientes;
    private DAOProducto daoProductos;
    private DAOPedido daoPedidos;

    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {

        Properties configuracion = new Properties();
        this.fa = fa;
        FileInputStream arqConfiguracion;

        try {

            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://" + configuracion.getProperty("servidor") + ":" + configuracion.getProperty("puerto") + "/" + configuracion.getProperty("baseDatos"), usuario);

            daoClientes = new DAOCliente(conexion, fa);
            daoProductos = new DAOProducto(conexion, fa);
            daoPedidos = new DAOPedido(conexion, fa);

        } catch (FileNotFoundException f) {

            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage(), Color.RED);

        } catch (IOException i) {

            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage(), Color.RED);

        } catch (java.sql.SQLException e) {

            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage(), Color.RED);

        }

    }

    public Cliente validarUsuario(String email, String password) {
        return daoClientes.validarUsuario(email, password);
    }

    public int actualizarCliente(Cliente c) {
        return daoClientes.actualizarCliente(c);
    }

    public int eliminarCliente(Cliente c) {
        return daoClientes.eliminarCliente(c);
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return daoProductos.obtenerProducts(nombre);
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return daoPedidos.obtenerPedidos(c);
    }

}
