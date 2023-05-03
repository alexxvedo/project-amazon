package gui;

import aplicacion.EmpresaVendedora;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaEmpresas extends AbstractTableModel {

    private java.util.List<EmpresaVendedora> empresas;

    public ModeloTablaEmpresas() {
        this.empresas = new java.util.ArrayList();
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return empresas.size();
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
                nombre = "Fecha Asociacion";
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
                clase = java.util.Date.class;
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
                resultado = empresas.get(row).getId();
                break;
            case 1:
                resultado = empresas.get(row).getNombre();
                break;
            case 2:
                resultado = empresas.get(row).getFechaAsociacion();
                break;

        }

        return resultado;

    }

    public void setFilas(java.util.List<EmpresaVendedora> empresas) {
        this.empresas = empresas;
        fireTableDataChanged();
    }

    public EmpresaVendedora obtenerEmpresa(int i) {
        return this.empresas.get(i);
    }

}
