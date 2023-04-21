package aplicacion;

public class MetodoPago {

    private int numeroTarjeta;
    private Cliente cliente;
    private boolean activa;
    private boolean preferida;

    public MetodoPago(int numeroTarjeta, Cliente cliente, boolean activa, boolean preferida) {
        this.numeroTarjeta = numeroTarjeta;
        this.cliente = cliente;
        this.activa = activa;
        this.preferida = preferida;
    }

    public int getNumeroTarjeta() {
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
    
}
