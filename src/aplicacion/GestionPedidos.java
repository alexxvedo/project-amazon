package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;
import java.util.HashMap;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con pedidos
public class GestionPedidos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    // Conjunto de productos y las cantidad de cada uno que tiene un cliente almacenada en su cesta
    HashMap<Producto, Integer> cestaProductos;

    public GestionPedidos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
        this.cestaProductos = new HashMap<>();
    }

    // Funcion que nos permite recuperar de la base de datos todos los pedidos de un cliente
    public ArrayList<Pedido> obtenerPedidos(Cliente c) {
        return fbd.obtenerPedidos(c);
    }

    // Funcion que nos permite crear un nuevo pedido
    public int crearPedido(Cliente c, MetodoPago m, Direccion d, Distribuidor dist, HashMap<Producto, Integer> p, boolean f) {

        // Creamos el pedido, guardando el codigo de informacion
        int res = fbd.crearPedido(c, m, d, dist, p, f);

        // Si se realizo correctamente, vaciamos la cesta
        if (res == 1) {

            this.cestaProductos = new HashMap<>();

        }

        // Devolvemos el codigo
        return res;

    }

    // Funcion para insertar un nuevo producto en la cesta
    public void insertarProducto(Producto p, int cantidad, boolean isUpdate) {

        // Comprobamos si ya existe el producto y que cantidad de el tenemos
        int existencias = this.cestaProductos.getOrDefault(p, 0);

        // En caso de que querramos actualizar la cantidad de un producto comprobamos si hay suficientes existencias
        if (isUpdate && cantidad <= p.getExistencias()) {

            // Actualizamos el valor almacenado en la cesta
            this.cestaProductos.put(p, cantidad);

            // Si no es una actualizacion si no que ya teniamos el producto y volvemos a querer meter el mismo, actualizamos la cantidad
        } else if (existencias + cantidad <= p.getExistencias()) {

            this.cestaProductos.put(p, existencias + cantidad);

        }

    }

    // Funcion para recuperar el estado actual de la cesta
    public HashMap<Producto, Integer> getCestaProductos() {
        return cestaProductos;
    }

    // Funcion para quitar un producto de la cesta
    public void eliminarProducto(Producto p) {

        this.cestaProductos.remove(p);

    }

}
