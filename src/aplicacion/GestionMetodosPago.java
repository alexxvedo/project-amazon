package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con metodos de pago
public class GestionMetodosPago {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionMetodosPago(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    // Funcion que nos permite ver la ventana para gestionar los metodos de pago de un cliente
    public void verVentaMetodosPago(Cliente c) {
        fgui.verVentanaMetodosPago(c);
    }

    // Funcion para recuperar todos los metodos de pago de un cliente
    public ArrayList<MetodoPago> obtenerMetodosPago(Cliente c) {
        return fbd.obtenerMetodosPago(c);
    }

    // Funcion que permite a un cliente crear un nuevo metodo de pago
    public int crearMetodoPago(MetodoPago m) {
        return fbd.crearMetodoPago(m);
    }

    // Funcion para actualizar los datos de un metodo de pago de un cliente
    public int actualizarMetodoPago(MetodoPago oldM, MetodoPago newM) {
        return fbd.actualizarMetodoPago(oldM, newM);
    }

    // Funcion que elimina un metodo de pago
    public int eliminarMetodoPago(MetodoPago m) {
        return fbd.eliminarMetodoPago(m);
    }

}
