package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;
import java.util.HashMap;

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

    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {
        return fbd.crearPedido(c, m, d, dist, p, f);
    }

}
