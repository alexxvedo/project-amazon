package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;
import java.util.HashMap;

public class GestionPedidos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    HashMap<Producto, Integer> cestaProductos;

    public GestionPedidos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
        this.cestaProductos = new HashMap<>();
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return fbd.obtenerPedidos(c);
    }

    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {
        
        int res = fbd.crearPedido(c, m, d, dist, p, f);
        
        if (res == 1) {
            
            this.cestaProductos = new HashMap<>();
            
        }
        
        return res;
        
    }
    
    public void insertarProducto(Producto p, int cantidad, boolean isUpdate) {
        
        int existencias = this.cestaProductos.getOrDefault(p, 0);
        
        if (isUpdate && cantidad <= p.getExistencias()) {
            
            this.cestaProductos.put(p, cantidad);
            
        } else if (existencias + cantidad <= p.getExistencias()) {
            
            this.cestaProductos.put(p, existencias + cantidad);
            
        }
        
    }

    public HashMap<Producto, Integer> getCestaProductos() {
        return cestaProductos;
    }
    
    public void eliminarProducto(Producto p) {
        
        this.cestaProductos.remove(p);
        
    }
    
}
