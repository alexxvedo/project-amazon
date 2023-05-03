package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

public class GestionProductos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionProductos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public ArrayList<Producto> obtenerProductos(String nombre) {
        return fbd.obtenerProductos(nombre);
    }

    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, String nombre, String descripcion, float precio, int existencias) {
        return fbd.crearProducto(selectedEmpresa, selectedAlmacen, nombre, descripcion, precio, existencias);
    }

}
