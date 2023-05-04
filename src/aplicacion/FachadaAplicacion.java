package aplicacion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

// Clase fachada que nos proporciona acceso a los distintos metodos y funcionalidades de la aplicacion
public class FachadaAplicacion {

    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionClientes cc;
    GestionProductos cp;
    GestionPedidos cpe;
    GestionDirecciones cd;
    GestionMetodosPago cmp;
    GestionDistribuidor cdis;
    GestionAlmacenes calm;
    GestionEmpresasVendedoras cemp;

    public FachadaAplicacion() {
        fgui = new gui.FachadaGui(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        cc = new GestionClientes(fgui, fbd);
        cp = new GestionProductos(fgui, fbd);
        cpe = new GestionPedidos(fgui, fbd);
        cd = new GestionDirecciones(fgui, fbd);
        cmp = new GestionMetodosPago(fgui, fbd);
        calm = new GestionAlmacenes(fgui, fbd);
        cemp = new GestionEmpresasVendedoras(fgui, fbd);
        cdis = new GestionDistribuidor(fbd);
    }

    public static void main(String args[]) {

        FachadaAplicacion fa;

        fa = new FachadaAplicacion();

        fa.iniciaInterfazUsuario();

    }

    // Funcion para iniciar la vista por defecto
    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
    }

    // Funcion para ver la ventana principal
    public void verVentanaPrincipal() {
        fgui.verVentanaPrincipal(cc.getCliente());
    }

    // Funcion para agregar un nuevo producto a la cesta
    public void insertarProductoCesta(Producto p, int cantidad, boolean isUpdate) {
        this.cpe.insertarProducto(p, cantidad, isUpdate);
    }

    // Funcion para eliminar un producto de la cesta
    public void eliminarProductoCesta(Producto p) {
        this.cpe.eliminarProducto(p);
    }

    // Funcion generica para mostrar mensajes
    public void muestraExcepcion(String e, Color c) {
        fgui.muestraExcepcion(e, c);
    }

    // Funcion empleada para el inicio de sesion
    public Boolean comprobarAutentificacion(String nombre, String password) {
        return cc.comprobarAutentificacion(nombre, password);
    }

    // Funcion que nos permite crear un producto
    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, Producto p) {
        return cp.crearProducto(selectedEmpresa, selectedAlmacen, p);
    }

    // Funcion para crear una nueva direccion al cliente activo
    public int crearDireccion(Direccion d) {
        return cd.crearDireccion(d);
    }

    // Funcion para crear un nuevo metodo de pago para el cliente activo
    public int crearMetodoPago(MetodoPago m) {
        return cmp.crearMetodoPago(m);
    }

    // Funcion para crear un cliente
    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {
        return cpe.crearPedido(c, m, d, dist, p, f);
    }

    // Funcion para permitir a un nuevo cliente que se registre en el sistema
    public int crearCliente(Cliente c) {
        return cc.crearCliente(c);
    }

    // Funcion para actualizar los datos de un cliente
    public int actualizarCliente(Cliente c) {
        return cc.actualizarCliente(c);
    }

    // Funcion para actualizar los datos de la direccion de envio de un cliente
    public int actualizarDireccion(Direccion d) {
        return cd.actualizarDireccion(d);
    }

    // Funcion para actualizar los datos de un metodo de pago
    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {
        return cmp.actualizarMetodoPago(oldM, newM);
    }

    // Funcion para eliminar un cliente
    public int eliminarCliente(Cliente c) {
        return cc.eliminarCliente(c);
    }

    // Funcion para eliminar una direccion de envio
    public int eliminarDireccion(Direccion d) {
        return cd.eliminarDireccion(d);
    }

    // Funcion para eliminar un almacen y mover sus productos a otro
    public int eliminarAlmacen(Almacen almacenEliminar, Almacen almacenDestino) {
        return calm.eliminarAlmacen(almacenEliminar, almacenDestino);
    }

    // Funcion para eliminar un metodo de pago de un cliente
    public int eliminarMetodoPago(MetodoPago m) {
        return cmp.eliminarMetodoPago(m);
    }

    // Funcion para recuperar todos los productos de la compania
    public ArrayList<Producto> obtenerProductos(String nombre) {
        return cp.obtenerProductos(nombre);
    }

    // Funcion para obtener el cliente activo en la aplicacion
    public Cliente obtenerCliente() {
        return cc.getCliente();
    }

    // Funcion para recuperar la cesta de instancia actual
    public HashMap<Producto, Integer> obtenerCesta() {
        return cpe.getCestaProductos();
    }

    // Funcion para recuperar todos los pedidos de un cliente
    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return cpe.obtenerPedidos(c);
    }

    // Funcion para recuperar de la base de datos todas las direcciones del cliente
    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return cd.obtenerDirecciones(c);
    }

    // Funcion para obtener todos los metodos de pago del cliente activo
    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return cmp.obtenerMetodosPago(c);
    }

    // Funcion para obtener el listado de todos los distribuidores actuales
    public ArrayList<Distribuidor> obtenerDistribuidores() {
        return cdis.obtenerDistribuidores();
    }

    // Funcion para recuperar todos los almacenes de la empresa
    public ArrayList<Almacen> obtenerAlmacenes() {
        return calm.obtenerAlmacenes();
    }

    // Funcion para recuperar todas las empresas que colaboran vendiendo con nosotros
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras() {
        return cemp.obtenerEmpresasVendedoras();
    }

    // Funcion que nos permite ver la ventana de perfil personal
    public void verPerfil() {
        cc.verVentanaPerfil(this.cc.getCliente());
    }

    // Funcion para que el administrador pueda gestionar almacenes
    public void verEliminarAlmacen() {
        calm.verEliminarAlmacen();
    }

    // Funcion para ver la ventana que permite que un cliente pueda gestionar sus metodos de pago
    public void verMetodosPago() {
        cmp.verVentaMetodosPago(this.cc.getCliente());
    }

    // Funcion para ver la ventana que permite que un cliente pueda gestionar sus direcciones de envio
    public void verDirecciones() {
        cd.verVentanaDirecciones(this.cc.getCliente());
    }

    // Funcion para ver la ventana que permite que un cliente pueda contratar el prime
    public void verObtenerPrime() {
        cc.verVentanaObtenerPrime(this.cc.getCliente());
    }

    // Funcion para ver la ventana que permite que un cliente vea su cesta de producto y realice la compra
    public void verVentanaCesta() {
        fgui.verVentanaCesta(this.cc.getCliente());
    }

    // Funcion para ver la ventana que permite que un administrador agregue nuevos productos
    public void verVentanaAnhadirProductos() {
        fgui.verVentanaAnhadirProducto();
    }

}
