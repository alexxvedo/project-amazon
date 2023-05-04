package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con empresas que venden a traves de nosotros
public class GestionEmpresasVendedoras {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionEmpresasVendedoras(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    // Funcion que nos permite recuperar el listado de empresas que venden a traves de nosotros
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras() {
        return fbd.obtenerEmpresasVendedoras();
    }

}
