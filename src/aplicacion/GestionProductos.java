package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con productos
public class GestionProductos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionProductos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    // Funcion que nos permite obtener todos los productos de la base de datos
    public ArrayList<Producto> obtenerProductos(String nombre) {
        return fbd.obtenerProductos(nombre);
    }

    // Funcion que nos permite crear un nuevo producto
    public int crearProducto(EmpresaVendedora selectedEmpresa, Almacen selectedAlmacen, Producto p) {
        return fbd.crearProducto(selectedEmpresa, selectedAlmacen, p);
    }

}
