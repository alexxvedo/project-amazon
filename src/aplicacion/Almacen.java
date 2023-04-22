package aplicacion;

public class Almacen {

    private int id;
    private String calle;
    private int numero;
    private String ciudad;
    private int codigoPostal;

    public Almacen(int id, String calle, int numero, String ciudad, int codigoPostal) {
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

    public int getCodigoPostal() {
        return codigoPostal;
    }

    @Override
    public String toString() {
        return "Almacen{" + "id=" + id + '}';
    }

}
