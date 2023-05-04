package aplicacion;

// Entidad distribuidor de la base de datos
// Representa los distribuidores que pueden enviar un pedido
public class Distribuidor {

    // Atributos propios que representan las columnas de las tablas
    private int id;
    private String nombre;
    private int telefono;
    private float costeEnvio;

    public Distribuidor(int id, String nombre, int telefono, float costeEnvio) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.costeEnvio = costeEnvio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public float getCosteEnvio() {
        return costeEnvio;
    }

    @Override
    public String toString() {
        return "Distribuidor{" + "id=" + id + '}';
    }

}
