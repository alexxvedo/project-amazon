package gui;

import aplicacion.Cliente;
import aplicacion.Direccion;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaDirecciones extends AbstractTableModel {

    private java.util.List<Direccion> direcciones;

    public ModeloTablaDirecciones() {
        this.direcciones = new java.util.ArrayList();
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return direcciones.size();
    }

    @Override
    public String getColumnName(int col) {

        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Id";
                break;
            case 1:
                nombre = "Cliente";
                break;
            case 2:
                nombre = "Calle";
                break;
            case 3:
                nombre = "Numero";
                break;
            case 4:
                nombre = "Ciudad";
                break;
            case 5:
                nombre = "Cod. Postal";
                break;
            case 6:
                nombre = "Preferida";
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
                clase = Cliente.class;
                break;
            case 2:
                clase = java.lang.String.class;
                break;
            case 3:
                clase = java.lang.Integer.class;
                break;
            case 4:
                clase = java.lang.String.class;
                break;
            case 5:
                clase = java.lang.Integer.class;
                break;
            case 6:
                clase = java.lang.Boolean.class;
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
                resultado = direcciones.get(row).getId();
                break;
            case 1:
                resultado = direcciones.get(row).getCliente();
                break;
            case 2:
                resultado = direcciones.get(row).getCalle();
                break;
            case 3:
                resultado = direcciones.get(row).getNumero();
                break;
            case 4:
                resultado = direcciones.get(row).getCiudad();
                break;
            case 5:
                resultado = direcciones.get(row).getCodigoPostal();
                break;
            case 6:
                resultado = direcciones.get(row).isPreferida();
                break;
        }

        return resultado;

    }

    public void setFilas(java.util.List<Direccion> direcciones) {
        this.direcciones = direcciones;
        fireTableDataChanged();
    }

    public Direccion obtenerDireccion(int i) {
        return this.direcciones.get(i);
    }

}
