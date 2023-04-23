package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

public class GestionPedidos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionPedidos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return fbd.obtenerPedidos(c);
    }

}
