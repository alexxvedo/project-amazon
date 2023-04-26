package aplicacion;

import java.awt.Color;
import java.util.ArrayList;

public class FachadaAplicacion {

    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionClientes cc;
    GestionProductos cp;
    GestionPedidos cpe;
    GestionDirecciones cd;
    GestionMetodosPago cmp;

    public FachadaAplicacion() {
        fgui = new gui.FachadaGui(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        cc = new GestionClientes(fgui, fbd);
        cp = new GestionProductos(fgui, fbd);
        cpe = new GestionPedidos(fgui, fbd);
        cd = new GestionDirecciones(fgui, fbd);
        cmp = new GestionMetodosPago(fgui, fbd);
    }

    public static void main(String args[]) {

        FachadaAplicacion fa;

        fa = new FachadaAplicacion();
        fa.iniciaInterfazUsuario();

    }

    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e, Color c) {
        fgui.muestraExcepcion(e, c);
    }

    public Boolean comprobarAutentificacion(String nombre, String password) {
        return cc.comprobarAutentificacion(nombre, password);
    }

    public int crearDireccion(Cliente c, Direccion d) {
        return cd.crearDireccion(c, d);
    }

    public int crearMetodoPago(MetodoPago m) {
        return cmp.crearMetodoPago(m);
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

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return cpe.obtenerPedidos(c);
    }

    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return cd.obtenerDirecciones(c);
    }

    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return cmp.obtenerMetodosPago(c);
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

}
