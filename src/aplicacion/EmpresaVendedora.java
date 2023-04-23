package aplicacion;

import java.util.Date;

public class EmpresaVendedora {

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
