package aplicacion;

// Entidad direccion de la base de datos
// Representa las posibles direccion de un cliente a donde se puede enviar un pedido
public class Direccion {

    // Atributos propios que representan las columnas de las tablas
    private int id;
    private Cliente cliente;
    private String calle;
    private int numero;
    private String ciudad;
    private int codigoPostal;
    private boolean preferida;

    public Direccion(int id, Cliente cliente, String calle, int numero, String ciudad, int codigoPostal, boolean preferida) {
        this.id = id;
        this.cliente = cliente;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.preferida = preferida;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public boolean isPreferida() {
        return preferida;
    }

    @Override
    public String toString() {
        return "Direccion{" + "id=" + id + '}';
    }

}
