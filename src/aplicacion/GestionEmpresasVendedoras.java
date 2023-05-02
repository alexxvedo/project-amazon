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
public class GestionEmpresasVendedoras {
    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionEmpresasVendedoras(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }


    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras() {
        return fbd.obtenerEmpresasVendedoras();
    }

}
