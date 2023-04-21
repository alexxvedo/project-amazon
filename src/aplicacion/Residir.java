package aplicacion;

public class Residir {

    private Cliente cliente;
    private Direccion direccion;

    public Residir(Cliente cliente, Direccion direccion) {
        this.cliente = cliente;
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

}
