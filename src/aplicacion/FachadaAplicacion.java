package aplicacion;

import java.util.ArrayList;

public class FachadaAplicacion {

    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionUsuarios cu;
    GestionProductos cp;

    public FachadaAplicacion() {
        fgui = new gui.FachadaGui(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        cu = new GestionUsuarios(fgui, fbd);
        cp = new GestionProductos(fgui, fbd);
    }

    public static void main(String args[]) {

        FachadaAplicacion fa;

        fa = new FachadaAplicacion();
        fa.iniciaInterfazUsuario();

    }

    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e) {
        fgui.muestraExcepcion(e);
    }

    public Boolean comprobarAutentificacion(String nombre, String password) {
        return cu.comprobarAutentificacion(nombre, password);
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return cp.obtenerProductos(nombre);
    }

}
