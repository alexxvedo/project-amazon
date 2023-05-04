package aplicacion;

// Entidad metodos de pago de la base de datos
// Representa los metodos de pago de un cliente, que tiene disponibles en la aplicacion
public class MetodoPago {

    // Atributos propios que representan las columnas de las tablas
    private long numeroTarjeta;
    private Cliente cliente;
    private boolean activa;
    private boolean preferida;

    public MetodoPago(long numeroTarjeta, Cliente cliente, boolean activa, boolean preferida) {
        this.numeroTarjeta = numeroTarjeta;
        this.cliente = cliente;
        this.activa = activa;
        this.preferida = preferida;
    }

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isActiva() {
        return activa;
    }

    public boolean isPreferida() {
        return preferida;
    }

    @Override
    public String toString() {
        return "MetodoPago{" + "numeroTarjeta=" + numeroTarjeta + '}';
    }

}
