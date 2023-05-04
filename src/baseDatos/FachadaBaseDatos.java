package baseDatos;

import aplicacion.Almacen;
import aplicacion.Cliente;
import aplicacion.Direccion;
import aplicacion.Distribuidor;
import aplicacion.MetodoPago;
import aplicacion.Pedido;
import aplicacion.Producto;
import aplicacion.EmpresaVendedora;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

// Clase fachada que nos proporciona acceso a los distintos metodos y funcionalidades de la aplicacion en lo referente a la base de datos
public class FachadaBaseDatos {

    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOCliente daoClientes;
    private DAOProducto daoProductos;
    private DAOPedido daoPedidos;
    private DAODireccion daoDireccion;
    private DAOMetodoPago daoMetodoPago;
    private DAODistribuidor daoDistribuidor;
    private DAOAlmacen daoAlmacen;
    private DAOEmpresasVendedoras daoEmpresasVendedoras;

    // Configuracion inicial de la conexion
    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {

        // Variables que emplearemos
        Properties configuracion = new Properties();
        this.fa = fa;
        FileInputStream arqConfiguracion;

        try {

            // Indicamos que tenemos la configuracion en un fichero
            // Lo abrimos y recuperamos los datos que contiene
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            // Indicamos los datos del usuario que emplearemos
            Properties usuario = new Properties();

            String gestor = configuracion.getProperty("gestor");

            // Indicamos sus atributos
            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));

            // Establecemos la conexion
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://" + configuracion.getProperty("servidor") + ":" + configuracion.getProperty("puerto") + "/" + configuracion.getProperty("baseDatos"), usuario);

            // Establecemos la configuracion por defecto
            this.conexion.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
            this.conexion.setAutoCommit(true);

            // Inicializamos todos los gestores
            daoClientes = new DAOCliente(conexion, fa);
            daoProductos = new DAOProducto(conexion, fa);
            daoPedidos = new DAOPedido(conexion, fa);
            daoDireccion = new DAODireccion(conexion, fa);
            daoMetodoPago = new DAOMetodoPago(conexion, fa);
            daoDistribuidor = new DAODistribuidor(conexion, fa);
            daoAlmacen = new DAOAlmacen(conexion, fa);
            daoEmpresasVendedoras = new DAOEmpresasVendedoras(conexion, fa);

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

    // Funcion empleada para el inicio de sesion
    public Cliente validarUsuario(String email, String password) {
        return daoClientes.validarUsuario(email, password);
    }

    // Funcion para crear una nueva direccion al cliente activo
    public int crearDireccion(Direccion d) {
        return daoDireccion.crearDireccion(d);
    }

    // Funcion para crear un nuevo metodo de pago para el cliente activo
    public int crearMetodoPago(MetodoPago m) {
        return daoMetodoPago.crearMetodoPago(m);
    }

    // Funcion que nos permite crear un producto
    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, Producto p) {
        return daoProductos.crearProducto(selectedEmpresa, selectedAlmacen, p);
    }

    // Funcion para crear un cliente
    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {
        return daoPedidos.crearPedido(c, m, d, dist, p, f);
    }

    // Funcion para permitir a un nuevo cliente que se registre en el sistema
    public int crearCliente(Cliente c) {
        return daoClientes.crearCliente(c);
    }

    // Funcion para actualizar los datos de un cliente
    public int actualizarCliente(Cliente c) {
        return daoClientes.actualizarCliente(c);
    }

    // Funcion para actualizar los datos de un metodo de pago
    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {
        return daoMetodoPago.actualizarMetodoPago(oldM, newM);
    }

    // Funcion para actualizar los datos de la direccion de envio de un cliente
    public int actualizarDireccion(Direccion d) {
        return daoDireccion.actualizarDireccion(d);
    }

    // Funcion para eliminar un cliente
    public int eliminarCliente(Cliente c) {
        return daoClientes.eliminarCliente(c);
    }

    // Funcion para eliminar una direccion de envio
    public int eliminarDireccion(Direccion d) {
        return daoDireccion.eliminarDireccion(d);
    }

    // Funcion para eliminar un almacen y mover sus productos a otro
    public int eliminarAlmacen(Almacen almacenEliminar, Almacen almacenDestino) {
        return daoAlmacen.eliminarAlmacen(almacenEliminar, almacenDestino);
    }

    // Funcion para eliminar un metodo de pago de un cliente
    public int eliminarMetodoPago(MetodoPago m) {
        return daoMetodoPago.eliminarMetodoPago(m);
    }

    // Funcion para recuperar todos los productos de la compania
    public ArrayList<Producto> obtenerProductos(String nombre) {
        return daoProductos.obtenerProducts(nombre);
    }

    // Funcion para recuperar todos los pedidos de un cliente
    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return daoPedidos.obtenerPedidos(c);
    }

    // Funcion para recuperar de la base de datos todas las direcciones del cliente
    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return daoDireccion.obtenerDirecciones(c);
    }

    // Funcion para obtener todos los metodos de pago del cliente activo
    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return daoMetodoPago.obtenerMetodosPago(c);
    }

    // Funcion para obtener el listado de todos los distribuidores actuales
    public ArrayList<Distribuidor> obtenerDistribuidores() {
        return daoDistribuidor.obtenerDistribuidores();
    }

    // Funcion para recuperar todos los almacenes de la empresa
    public ArrayList<Almacen> obtenerAlmacenes() {
        return daoAlmacen.obtenerAlmacenes();
    }

    // Funcion para recuperar todas las empresas que colaboran vendiendo con nosotros
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras() {
        return daoEmpresasVendedoras.obtenerEmpresasVendedoras();
    }

}
