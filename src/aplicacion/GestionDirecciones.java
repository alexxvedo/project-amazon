package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con direcciones
public class GestionDirecciones {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionDirecciones(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    // Funcion que nos permite ver la ventana para gestionar sus direcciones de envio
    public void verVentanaDirecciones(Cliente c) {
        fgui.verVentanaDirecciones(c);
    }

    // Funcion que nos permite obtener un listado con todas las direccion de envio de un cliente
    public ArrayList<Direccion> obtenerDirecciones(Cliente c) {
        return fbd.obtenerDirecciones(c);
    }

    // Funciones que permite crear una nueva direccion de envio
    public int crearDireccion(Direccion d) {
        return fbd.crearDireccion(d);
    }

    // Funciones que permite actualiza los datos de una direccion de envio
    public int actualizarDireccion(Direccion d) {
        return fbd.actualizarDireccion(d);
    }

    // Funciones que permite eliminar una direccion de envio
    public int eliminarDireccion(Direccion d) {
        return fbd.eliminarDireccion(d);
    }

}
