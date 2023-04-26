package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

public class GestionMetodosPago {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionMetodosPago(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void verVentaMetodosPago(Cliente c) {
        fgui.verVentanaMetodosPago(c);
    }

    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return fbd.obtenerMetodosPago(c);
    }

    public int crearMetodoPago(MetodoPago m) {
        return fbd.crearMetodoPago(m);
    }

    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {
        return fbd.actualizarMetodoPago(oldM, newM);
    }

    public int eliminarMetodoPago(MetodoPago m) {
        return fbd.eliminarMetodoPago(m);
    }

}
