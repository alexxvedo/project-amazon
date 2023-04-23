package aplicacion;

import java.awt.Color;
import java.util.ArrayList;

public class FachadaAplicacion {

    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionClientes cc;
    GestionProductos cp;
    GestionPedidos cpe;

    public FachadaAplicacion() {
        fgui = new gui.FachadaGui(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        cc = new GestionClientes(fgui, fbd);
        cp = new GestionProductos(fgui, fbd);
        cpe = new GestionPedidos(fgui, fbd);
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

    public int actualizarCliente(Cliente c) {
        return cc.actualizarCliente(c);
    }

    public int eliminarCliente(Cliente c) {
        return cc.eliminarCliente(c);
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return cp.obtenerProductos(nombre);
    }
    
    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return cpe.obtenerPedidos(c);
    }

    public void verPerfil() {
        cc.verPerfil();
    }

    public void verMetodosPago() {
        // TODO
    }

    public void verDirecciones() {
        // TODO
    }

}
