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
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

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

    public Cliente validarUsuario(String email, String password) {
        return daoClientes.validarUsuario(email, password);
    }

    public int crearDireccion(Direccion d) {
        return daoDireccion.crearDireccion(d);
    }

    public int crearMetodoPago(MetodoPago m) {
        return daoMetodoPago.crearMetodoPago(m);
    }
    
    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen,String nombre,String descripcion,float precio, int existencias){
        return daoProductos.crearProducto(selectedEmpresa, selectedAlmacen, nombre, descripcion, precio, existencias);
    }

    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {
        return daoPedidos.crearPedido(c, m, d, dist, p, f);
    }
    
    public int crearCliente(String nombre, String email, String password, String fechaNacimiento, boolean prime, int telefono){
        return daoClientes.crearCliente(nombre, email, password, fechaNacimiento, prime, telefono);
    }

    public int actualizarCliente(Cliente c) {
        return daoClientes.actualizarCliente(c);
    }

    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {
        return daoMetodoPago.actualizarMetodoPago(oldM, newM);
    }

    public int actualizarDireccion(Direccion d) {
        return daoDireccion.actualizarDireccion(d);
    }

    public int eliminarCliente(Cliente c) {
        return daoClientes.eliminarCliente(c);
    }

    public int eliminarDireccion(Direccion d) {
        return daoDireccion.eliminarDireccion(d);
    }

    public int eliminarMetodoPago(MetodoPago m) {
        return daoMetodoPago.eliminarMetodoPago(m);
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return daoProductos.obtenerProducts(nombre);
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return daoPedidos.obtenerPedidos(c);
    }

    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return daoDireccion.obtenerDirecciones(c);
    }

    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return daoMetodoPago.obtenerMetodosPago(c);
    }
    
    public ArrayList<Distribuidor> obtenerDistribuidores(){
        return daoDistribuidor.obtenerDistribuidores();
    }
    
    public ArrayList<Almacen> obtenerAlmacenes(){
        return daoAlmacen.obtenerAlmacenes();
    }
    
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras(){
        return daoEmpresasVendedoras.obtenerEmpresasVendedoras();
    }

}
