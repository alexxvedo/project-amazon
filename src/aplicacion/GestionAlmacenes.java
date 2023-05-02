/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class GestionAlmacenes {
    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionAlmacenes(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }


    public ArrayList<Almacen> obtenerAlmacenes() {
        return fbd.obtenerAlmacenes();
    }

}
