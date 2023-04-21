package aplicacion;

public class Producto {

    private int id;
    private EmpresaVendedora empresa;
    private Almacen almacen;
    private String nombre;
    private String descripcion;
    private float precio;
    private int existencias;

    public Producto(int id, EmpresaVendedora empresa, Almacen almacen, String nombre, String descripcion, float precio, int existencias) {
        this.id = id;
        this.empresa = empresa;
        this.almacen = almacen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }

    public int getId() {
        return id;
    }

    public EmpresaVendedora getEmpresa() {
        return empresa;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }
    
}
