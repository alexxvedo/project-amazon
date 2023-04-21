package aplicacion;

public class Almacen {
 
    private int id;
    private String calle;
    private int numero;
    private String ciudad;
    private String codigoPostal;

    public Almacen(int id, String calle, int numero, String ciudad, String codigoPostal) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }
    
}
