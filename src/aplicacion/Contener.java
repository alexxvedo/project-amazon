package aplicacion;

// Entidad contener de la base de datos, relaciona pedidos y los productos que estos contienen
// Representa la relacion N:N de la base de datos, entre pedidos y productos
public class Contener {

    // Atributos propios que representan las columnas de las tablas
    private Pedido pedido;
    private Producto producto;
    private int cantidad;

    public Contener(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

}
