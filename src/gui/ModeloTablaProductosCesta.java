package gui;

import aplicacion.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.*;

public class ModeloTablaProductosCesta extends AbstractTableModel {

    private java.util.List<Producto> productos;
    private java.util.List<Integer> cantidades;

    public ModeloTablaProductosCesta() {
        this.productos = new java.util.ArrayList();
        this.cantidades = new java.util.ArrayList<>();
    }

    public int getColumnCount() {
        return 5;
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
                nombre = "Nombre";
                break;
            case 2:
                nombre = "Descripcion";
                break;
            case 3:
                nombre = "Precio";
                break;
            case 4:
                nombre = "Cantidad";
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
                clase = java.lang.String.class;
                break;
            case 2:
                clase = java.lang.String.class;
                break;
            case 3:
                clase = java.lang.Float.class;
                break;
            case 4:
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
                resultado = productos.get(row).getNombre();
                break;
            case 2:
                resultado = productos.get(row).getDescripcion();
                break;
            case 3:
                resultado = productos.get(row).getPrecio();
                break;
            case 4:
                resultado = cantidades.get(row);
                break;
        }

        return resultado;

    }

    public void setFilas(HashMap<Producto, Integer> productos) {

        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();

        for (Map.Entry<Producto, Integer> set : productos.entrySet()) {
            Producto key = set.getKey();
            Integer value = set.getValue();
            this.productos.add(key);
            this.cantidades.add(value);
        }

        fireTableDataChanged();
    }

    public Producto obtenerProducto(int i) {
        return this.productos.get(i);
    }

}
