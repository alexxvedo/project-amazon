package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

public class GestionAlmacenes {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionAlmacenes(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }
    
    public void verEliminarAlmacen() {
        fgui.verEliminarAlmacen();
    }

    public ArrayList<Almacen> obtenerAlmacenes() {
        return fbd.obtenerAlmacenes();
    }
    
    public int eliminarAlmacen(Almacen almacenEliminar, Almacen almacenDestino) {
        return fbd.eliminarAlmacen(almacenEliminar, almacenDestino);
    }

}
