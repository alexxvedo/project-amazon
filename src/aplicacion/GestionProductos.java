package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

public class GestionProductos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionProductos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return fbd.obtenerProductos(nombre);
    }

}
