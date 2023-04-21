package aplicacion;

import java.util.Date;

public class Valorar {

    private Cliente cliente;
    private Producto producto;
    private Date fecha;
    private int nota;
    private String comentario;
    private int estadoLlegada;

    public Valorar(Cliente cliente, Producto producto, Date fecha, int nota, String comentario, int estadoLlegada) {
        this.cliente = cliente;
        this.producto = producto;
        this.fecha = fecha;
        this.nota = nota;
        this.comentario = comentario;
        this.estadoLlegada = estadoLlegada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public int getEstadoLlegada() {
        return estadoLlegada;
    }

}
