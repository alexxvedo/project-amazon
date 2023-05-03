package aplicacion;

import java.util.Date;

public class Informar {

    private Cliente cliente;
    private EmpresaVendedora empresa;
    private Date fecha;
    private String descripcion;

    public Informar(Cliente cliente, EmpresaVendedora empresa, Date fecha, String descripcion) {
        this.cliente = cliente;
        this.empresa = empresa;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public EmpresaVendedora getEmpresa() {
        return empresa;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
