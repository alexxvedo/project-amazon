package gui;

import aplicacion.Cliente;
import aplicacion.Direccion;
import aplicacion.Distribuidor;
import aplicacion.MetodoPago;
import aplicacion.Pedido;
import javax.swing.table.AbstractTableModel;

// Codigo de la tabla personalizada para pedidos
public class ModeloTablaPedidos extends AbstractTableModel {

    private java.util.List<Pedido> pedidos;

    public ModeloTablaPedidos() {
        this.pedidos = new java.util.ArrayList();
    }

    public int getColumnCount() {
        return 10;
    }

    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public String getColumnName(int col) {

        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Id";
                break;
            case 1:
                nombre = "Fecha pedido";
                break;
            case 2:
                nombre = "Fecha salida";
                break;
            case 3:
                nombre = "Fecha llegada";
                break;
            case 4:
                nombre = "Completado";
                break;
            case 5:
                nombre = "Precio total";
                break;
            case 6:
                nombre = "Tarjeta";
                break;
            case 7:
                nombre = "Direccion";
                break;
            case 8:
                nombre = "Cliente";
                break;
            case 9:
                nombre = "Distribuidor";
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
                clase = java.util.Date.class;
                break;
            case 2:
                clase = java.util.Date.class;
                break;
            case 3:
                clase = java.util.Date.class;
                break;
            case 4:
                clase = java.lang.Boolean.class;
                break;
            case 5:
                clase = java.lang.Float.class;
                break;
            case 6:
                clase = MetodoPago.class;
                break;
            case 7:
                clase = Direccion.class;
                break;
            case 8:
                clase = Cliente.class;
                break;
            case 9:
                clase = Distribuidor.class;
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
                resultado = pedidos.get(row).getId();
                break;
            case 1:
                resultado = pedidos.get(row).getFechaPedido();
                break;
            case 2:
                resultado = pedidos.get(row).getFechaSalida();
                break;
            case 3:
                resultado = pedidos.get(row).getFechaLlegada();
                break;
            case 4:
                resultado = pedidos.get(row).isCompletado();
                break;
            case 5:
                resultado = pedidos.get(row).getPrecioTotal();
                break;
            case 6:
                resultado = pedidos.get(row).getTarjeta() != null ? pedidos.get(row).getTarjeta().toString() : "";
                break;
            case 7:
                resultado = pedidos.get(row).getDireccion() != null ? pedidos.get(row).getDireccion().toString() : "";
                break;
            case 8:
                resultado = pedidos.get(row).getCliente() != null ? pedidos.get(row).getCliente().toString() : "";
                break;
            case 9:
                resultado = pedidos.get(row).getDistribuidor() != null ? pedidos.get(row).getDistribuidor().toString() : "";
                break;
        }

        return resultado;

    }

    public void setFilas(java.util.List<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    public Pedido obtenerPedido(int i) {
        return this.pedidos.get(i);
    }

}
