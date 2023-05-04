package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con almacen
public class GestionAlmacenes {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionAlmacenes(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }
    
    // Funcion para ver la ventana de gestion de almacenes
    public void verEliminarAlmacen() {
        fgui.verEliminarAlmacen();
    }

    // Funcion que nos recupera el listado de almacenes de la base de datos
    public ArrayList<Almacen> obtenerAlmacenes() {
        return fbd.obtenerAlmacenes();
    }
    
    // Funcion para eliminar un almacen
    public int eliminarAlmacen(Almacen almacenEliminar, Almacen almacenDestino) {
        return fbd.eliminarAlmacen(almacenEliminar, almacenDestino);
    }

}
