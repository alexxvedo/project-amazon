package gui;

import aplicacion.Almacen;
import aplicacion.EmpresaVendedora;
import aplicacion.Producto;
import javax.swing.table.*;

// Codigo de la tabla personalizada para productos
public class ModeloTablaProductos extends AbstractTableModel {

    private java.util.List<Producto> productos;

    public ModeloTablaProductos() {
        this.productos = new java.util.ArrayList();
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return productos.size();
    }

    @Override
    public String getColumnName(int col) {

        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Id";
                break;
            case 1:
                nombre = "Empresa";
                break;
            case 2:
                nombre = "Almacen";
                break;
            case 3:
                nombre = "Nombre";
                break;
            case 4:
                nombre = "Descripcion";
                break;
            case 5:
                nombre = "Precio";
                break;
            case 6:
                nombre = "Existencias";
                break;
        }

        return nombre;

    }

    @Override
    public Class getColumnClass(int col) {

        Class clase = null;

        switch (col) {
            case 0:
                clase = java.lang.Integer.class;
                break;
            case 1:
                clase = EmpresaVendedora.class;
                break;
            case 2:
                clase = Almacen.class;
                break;
            case 3:
                clase = java.lang.String.class;
                break;
            case 4:
                clase = java.lang.String.class;
                break;
            case 5:
                clase = java.lang.Float.class;
                break;
            case 6:
                clase = java.lang.Integer.class;
                break;
        }

        return clase;

    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Object getValueAt(int row, int col) {

        Object resultado = null;

        switch (col) {
            case 0:
                resultado = productos.get(row).getId();
                break;
            case 1:
                resultado = productos.get(row).getEmpresa().toString();
                break;
            case 2:
                resultado = productos.get(row).getAlmacen().toString();
                break;
            case 3:
                resultado = productos.get(row).getNombre();
                break;
            case 4:
                resultado = productos.get(row).getDescripcion();
                break;
            case 5:
                resultado = productos.get(row).getPrecio();
                break;
            case 6:
                resultado = productos.get(row).getExistencias();
                break;
        }

        return resultado;

    }

    public void setFilas(java.util.List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    public Producto obtenerProducto(int i) {
        return this.productos.get(i);
    }

}
