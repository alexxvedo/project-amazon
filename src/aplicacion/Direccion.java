package aplicacion;

public class Direccion {

    private int id;
    private String calle;
    private int numero;
    private String ciudad;
    private int codigoPostal;
    private boolean preferida;

    public Direccion(int id, String calle, int numero, String ciudad, int codigoPostal, boolean preferida) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.preferida = preferida;
    }

    public int getId() {
        return id;
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
    
}
