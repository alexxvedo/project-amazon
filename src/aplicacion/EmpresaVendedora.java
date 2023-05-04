package aplicacion;

// Entidad empresa vendedora de la base de datos
// Representa las empresas que venden sus productos a traves de amazon
import java.util.Date;

public class EmpresaVendedora {

    // Atributos propios que representan las columnas de las tablas
    private int id;
    private String nombre;
    private Date fechaAsociacion;

    public EmpresaVendedora(int id, String nombre, Date fechaAsociacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaAsociacion = fechaAsociacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    @Override
    public String toString() {
        return "EmpresaVendedora{" + "id=" + id + '}';
    }

}
