package gui;

import aplicacion.Cliente;
import java.util.*;
import aplicacion.MetodoPago;
import aplicacion.Producto;
import aplicacion.Direccion;
import aplicacion.Distribuidor;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VCesta extends javax.swing.JDialog {

    // Variables que empleamos en la ventana
    private Cliente cliente;
    private aplicacion.FachadaAplicacion fa;
    private boolean isProductEditing;
    private boolean isMetodoPagoEditing;
    private boolean isDireccionEditing;
    private Producto selectedProd;
    private MetodoPago selectedMetodoPago;
    private Direccion selectedDir;
    private Distribuidor distribuidorActual;

    private float totalPagar = 0f;

    public VCesta(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa, Cliente cliente) {

        super(parent, modal);

        this.cliente = cliente;
        this.fa = fa;

        initComponents();
        anhadirProductos();
        anhadirMetodosPago();
        anhadirDirecciones();
        setDistribuidor();
        getTotal();
        customBehavior();

    }

    // Funcion que nos permite calcular el coste total de la cesta
    private void getTotal() {

        // Seteamos el valor a 0
        this.totalPagar = 0;

        // Por cada producto que tengamos el la cesta, calculamos su coste
        for (Map.Entry<Producto, Integer> set : this.fa.obtenerCesta().entrySet()) {

            Producto key = set.getKey();
            Integer value = set.getValue();
            this.totalPagar += key.getPrecio() * value;

        }

        // En caso de no tener el prime le sumamos el coste del envio
        if (!cliente.isPrime()) {
            totalPagar += distribuidorActual.getCosteEnvio();
        }

        // Indicamos el total en la label correspondiente
        this.totalPagarLabel.setText("" + this.totalPagar);

    }

    // Funcion que nos devuelve un distribuidor aleatorio de entre todos los disponibles
    private void setDistribuidor() {
        ArrayList<Distribuidor> distribuidores = fa.obtenerDistribuidores();
        Random random = new Random();
        int randomNumber = random.nextInt(distribuidores.size());
        distribuidorActual = distribuidores.get(randomNumber);
    }

    // Funcion para cargar los datos en la tabla de productos
    private void anhadirProductos() {

        // Creamos el modelo
        ModeloTablaProductosCesta m = (ModeloTablaProductosCesta) this.tablaProductosCesta.getModel();

        // Insertamos los datos
        m.setFilas(this.fa.obtenerCesta());

    }

    // Funcion para cargar los datos en la tabla de metodos de pago
    private void anhadirMetodosPago() {

        // Creamos el modelo
        ModeloTablaMetodosPago m = (ModeloTablaMetodosPago) this.tablaMetodosPagoCesta.getModel();

        // Insertamos los datos
        m.setFilas(fa.obtenerMetodosPago(this.cliente));

    }

    // Funcion para cargar los datos en la tabla de direcciones de envio
    private void anhadirDirecciones() {

        // Creamos el modelo
        ModeloTablaDirecciones m = (ModeloTablaDirecciones) this.tablaDireccionesCesta.getModel();

        // Insertamos los datos
        m.setFilas(fa.obtenerDirecciones(this.cliente));

    }

    // Funcion para gestionar el comportamiento y propiedades de los distintos elementos
    private void customBehavior() {

        // Indicamos el modo de seleccion
        this.tablaProductosCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaDireccionesCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaMetodosPagoCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ocultamos los botonos hasta que cumplan la condicion para poder usarse
        this.eliminarBtn.setVisible(false);
        this.cantidadSpinner.setVisible(false);

        // Controlamos el evento de cuando se cambia el valor del spinner
        cantidadSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                // Actualizamos la cesta, contenida en el gestor
                fa.insertarProductoCesta(selectedProd, (int) cantidadSpinner.getValue(), true);

                // Recargamos la tabla
                anhadirProductos();

                // Recalculamos el total
                getTotal();

            }

        });

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaProductosCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isProductEditing = tablaProductosCesta.getSelectedRows().length != 0;

                // Mostramos los botonos si se cumplen las condiciones
                eliminarBtn.setVisible(isProductEditing);
                cantidadSpinner.setVisible(isProductEditing);

                // Si hay alguna fila seleccionada
                if (isProductEditing) {

                    // Obtenemos la fila indicada y la guardamos en el producto
                    int row = tablaProductosCesta.getSelectedRow();
                    selectedProd = ((ModeloTablaProductosCesta) tablaProductosCesta.getModel()).obtenerProducto(row);

                    // Obtenemos la cesta actual
                    HashMap<Producto, Integer> productos = fa.obtenerCesta();

                    // Indicamos el valor actual, minimo y maximo del spinner en base al estado de la cesta
                    cantidadSpinner.setModel(new SpinnerNumberModel((int) productos.get(selectedProd), 1, selectedProd.getExistencias(), 1));

                }

            }

        });

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaMetodosPagoCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isMetodoPagoEditing = tablaMetodosPagoCesta.getSelectedRows().length != 0;

                // Si hay alguna fila seleccionada
                if (isMetodoPagoEditing) {

                    // Obtenemos la fila y guardamos el metodo de pago seleccionado
                    int row = tablaMetodosPagoCesta.getSelectedRow();
                    selectedMetodoPago = ((ModeloTablaMetodosPago) tablaMetodosPagoCesta.getModel()).obtenerMetodoPago(row);

                }

            }

        });

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaDireccionesCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isDireccionEditing = tablaDireccionesCesta.getSelectedRows().length != 0;

                // Si hay alguna fila seleccionada
                if (isDireccionEditing) {

                    // Obtenemos la fila y guardamos la direccion seleccionada
                    int row = tablaDireccionesCesta.getSelectedRow();
                    selectedDir = ((ModeloTablaDirecciones) tablaDireccionesCesta.getModel()).obtenerDireccion(row);

                }

            }

        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        eliminarBtn = new javax.swing.JButton();
        pagarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMetodosPagoCesta = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDireccionesCesta = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        totalPagarLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProductosCesta = new javax.swing.JTable();
        cantidadSpinner = new javax.swing.JSpinner();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Productos:");

        jLabel2.setText("Metodo de pago:");

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        pagarBtn.setText("Pagar");
        pagarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        tablaMetodosPagoCesta.setModel(new ModeloTablaMetodosPago());
        jScrollPane3.setViewportView(tablaMetodosPagoCesta);

        jLabel3.setText("Direcciones:");

        tablaDireccionesCesta.setModel(new ModeloTablaDirecciones());
        jScrollPane2.setViewportView(tablaDireccionesCesta);

        jLabel4.setText("TOTAL:");

        tablaProductosCesta.setModel(new ModeloTablaProductosCesta());
        jScrollPane4.setViewportView(tablaProductosCesta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(eliminarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pagarBtn))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalPagarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(totalPagarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarBtn)
                    .addComponent(pagarBtn)
                    .addComponent(cancelarBtn)
                    .addComponent(cantidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed

        // Eliminamos el producto de la cesta, situada en el gestor
        this.fa.eliminarProductoCesta(selectedProd);

        // Actualizamos el contenido de la tabla
        this.anhadirProductos();

        // Recalculamos el total
        this.getTotal();

    }//GEN-LAST:event_eliminarBtnActionPerformed
    private void pagarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarBtnActionPerformed

        // Obtenemos el estado de la cesta actual
        HashMap<Producto, Integer> productos = fa.obtenerCesta();

        // Comprobamos que se cumplen todas las condiciones para poder realizar un pedido
        if (productos.isEmpty() || this.selectedDir == null || this.selectedMetodoPago == null) {
            return;
        }

        // Creamos el pedido a traves de la fachada
        int res = fa.crearPedido(this.cliente, this.selectedMetodoPago, this.selectedDir, distribuidorActual, productos, false);

        // Si el pedido se creo correctamente, mostramos un mensaje
        if (res == 1) {

            this.fa.muestraExcepcion("Pedido realizado correctamente", Color.GREEN);
            this.dispose();

        }

    }//GEN-LAST:event_pagarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JSpinner cantidadSpinner;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton pagarBtn;
    private javax.swing.JTable tablaDireccionesCesta;
    private javax.swing.JTable tablaMetodosPagoCesta;
    private javax.swing.JTable tablaProductosCesta;
    private javax.swing.JLabel totalPagarLabel;
    // End of variables declaration//GEN-END:variables
}
