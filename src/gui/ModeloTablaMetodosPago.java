package gui;

import aplicacion.Cliente;
import aplicacion.MetodoPago;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

// Codigo de la tabla personalizada para metodos de pago
public class ModeloTablaMetodosPago extends AbstractTableModel {

    private java.util.List<MetodoPago> metodosPago;

    public ModeloTablaMetodosPago() {
        this.metodosPago = new ArrayList<>();
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return metodosPago.size();
    }

    @Override
    public String getColumnName(int col) {

        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Numero tarjeta";
                break;
            case 1:
                nombre = "Cliente";
                break;
            case 2:
                nombre = "Activa";
                break;
            case 3:
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
                clase = java.lang.Long.class;
                break;
            case 1:
                clase = Cliente.class;
                break;
            case 2:
                clase = java.lang.Boolean.class;
                break;
            case 3:
                clase = java.lang.Boolean.class;
                break;
        }

        return clase;

    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col != 1;
    }

    public Object getValueAt(int row, int col) {

        Object resultado = null;

        switch (col) {
            case 0:
                resultado = metodosPago.get(row).getNumeroTarjeta();
                break;
            case 1:
                resultado = metodosPago.get(row).getCliente();
                break;
            case 2:
                resultado = metodosPago.get(row).isActiva();
                break;
            case 3:
                resultado = metodosPago.get(row).isPreferida();
                break;
        }

        return resultado;

    }

    public void setFilas(java.util.List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
        fireTableDataChanged();
    }

    public MetodoPago obtenerMetodoPago(int i) {
        return this.metodosPago.get(i);
    }

}
