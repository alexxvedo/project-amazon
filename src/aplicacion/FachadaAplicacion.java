package aplicacion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
        System.out.println(cc.getCliente().toString());
        fgui.verVentanaPrincipal(cc.getCliente());
    }
    
    

    public void insertarProductoCesta(Producto p, int cantidad, boolean isUpdate) {
        this.cpe.insertarProducto(p, cantidad, isUpdate);
    }

    public void eliminarProductoCesta(Producto p) {
        this.cpe.eliminarProducto(p);
    }

    public void muestraExcepcion(String e, Color c) {
        fgui.muestraExcepcion(e, c);
    }

    public Boolean comprobarAutentificacion(String nombre, String password) {
        return cc.comprobarAutentificacion(nombre, password);
    }
    
    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, String nombre, String descripcion, float precio, int existencias){
        return cp.crearProducto(selectedEmpresa, selectedAlmacen, nombre, descripcion, precio, existencias);
    }

    public int crearDireccion(Direccion d) {
        return cd.crearDireccion(d);
    }

    public int crearMetodoPago(MetodoPago m) {
        return cmp.crearMetodoPago(m);
    }

    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {
        return cpe.crearPedido(c, m, d, dist, p, f);
    }

    public int actualizarCliente(Cliente c) {
        return cc.actualizarCliente(c);
    }

    public int actualizarDireccion(Direccion d) {
        return cd.actualizarDireccion(d);
    }

    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {
        return cmp.actualizarMetodoPago(oldM, newM);
    }

    public int eliminarCliente(Cliente c) {
        return cc.eliminarCliente(c);
    }

    public int eliminarDireccion(Direccion d) {
        return cd.eliminarDireccion(d);
    }

    public int eliminarMetodoPago(MetodoPago m) {
        return cmp.eliminarMetodoPago(m);
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return cp.obtenerProductos(nombre);
    }
    
    public Cliente obtenerCliente(){
        return cc.getCliente();
    }
    
    public HashMap<Producto, Integer> obtenerCesta() {
        return cpe.getCestaProductos();
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return cpe.obtenerPedidos(c);
    }

    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return cd.obtenerDirecciones(c);
    }

    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return cmp.obtenerMetodosPago(c);
    }

    public ArrayList<Distribuidor> obtenerDistribuidores() {
        return cdis.obtenerDistribuidores();
    }
    
    public ArrayList<Almacen> obtenerAlmacenes(){
        return calm.obtenerAlmacenes();
    }
    
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras(){
        return cemp.obtenerEmpresasVendedoras();
    }
    
    

    public void verPerfil() {
        cc.verVentanaPerfil(this.cc.getCliente());
    }
    
    

    public void verMetodosPago() {
        cmp.verVentaMetodosPago(this.cc.getCliente());
    }

    public void verDirecciones() {
        cd.verVentanaDirecciones(this.cc.getCliente());
    }

    public void verObtenerPrime() {
        cc.verVentanaObtenerPrime(this.cc.getCliente());
    }

    public void verVentanaCesta() {
        fgui.verVentanaCesta(this.cc.getCliente());
    }
    
    public void verVentanaAnhadirProductos(){
        fgui.verVentanaAnhadirProducto();
    }

}
