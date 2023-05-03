package gui;

import aplicacion.Almacen;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaAlmacen extends AbstractTableModel {

    private java.util.List<Almacen> almacenes;

    public ModeloTablaAlmacen() {
        this.almacenes = new java.util.ArrayList();
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return almacenes.size();
    }

    @Override
    public String getColumnName(int col) {

        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Id";
                break;
            case 1:
                nombre = "Calle";
                break;
            case 2:
                nombre = "Numero";
                break;
            case 3:
                nombre = "Ciudad";
                break;
            case 4:
                nombre = "Codigo Postal";
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
                clase = java.lang.Integer.class;
                break;
            case 3:
                clase = java.lang.String.class;
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
                resultado = almacenes.get(row).getId();
                break;
            case 1:
                resultado = almacenes.get(row).getCalle();
                break;
            case 2:
                resultado = almacenes.get(row).getNumero();
                break;
            case 3:
                resultado = almacenes.get(row).getCiudad();
                break;
            case 4:
                resultado = almacenes.get(row).getCodigoPostal();
                break;

        }

        return resultado;

    }

    public void setFilas(java.util.List<Almacen> almacenes) {
        this.almacenes = almacenes;
        fireTableDataChanged();
    }

    public Almacen obtenerAlmacen(int i) {
        return this.almacenes.get(i);
    }

}
