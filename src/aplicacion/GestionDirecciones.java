package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

public class GestionDirecciones {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionDirecciones(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void verVentanaDirecciones(Cliente c) {
        fgui.verVentanaDirecciones(c);
    }

    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return fbd.obtenerDirecciones(c);
    }

    public int crearDireccion(Cliente c, Direccion d) {
        return fbd.crearDireccion(c, d);
    }

    public int actualizarDireccion(Direccion d) {
        return fbd.actualizarDireccion(d);
    }

    public int eliminarDireccion(Direccion d) {
        return fbd.eliminarDireccion(d);
    }

}
