package aplicacion;

import java.util.Date;

// Entidad pedido de la base de datos
// Representa los pedidos que realizo un cliente a traves de la aplicacion
public class Pedido {

    // Atributos propios que representan las columnas de las tablas
    private int id;
    private Date fechaPedido;
    private Date fechaSalida;
    private Date fechaLlegada;
    private boolean completado;
    private float precioTotal;
    private MetodoPago tarjeta;
    private Direccion direccion;
    private Cliente cliente;
    private Distribuidor distribuidor;

    public Pedido(int id, Date fechaPedido, Date fechaSalida, Date fechaLlegada, boolean completado, float precioTotal, MetodoPago tarjeta, Direccion direccion, Cliente cliente, Distribuidor distribuidor) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.completado = completado;
        this.precioTotal = precioTotal;
        this.tarjeta = tarjeta;
        this.direccion = direccion;
        this.cliente = cliente;
        this.distribuidor = distribuidor;
    }

    public int getId() {
        return id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public boolean isCompletado() {
        return completado;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public MetodoPago getTarjeta() {
        return tarjeta;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + '}';
    }

}
