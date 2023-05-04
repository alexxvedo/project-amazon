package gui;

import aplicacion.Almacen;
import aplicacion.EmpresaVendedora;
import aplicacion.Producto;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VAnhadirProducto extends javax.swing.JDialog {

    private aplicacion.FachadaAplicacion fa;
    private boolean isEmpresaEditing = false;
    private boolean isAlmacenEditing = false;
    private EmpresaVendedora selectedEmpresa = null;
    private Almacen selectedAlmacen = null;
    private String nombre;
    private int existencias;
    private float precio;
    private String descripcion;

    public VAnhadirProducto(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa) {
        super(parent, modal);
        this.fa = fa;
        initComponents();
        anhadirAlmacenes();
        anhadirEmpresasVendedoras();
        customBehavior();
    }

    // Funcion para cargar los datos en la tabla de almacenes
    private void anhadirAlmacenes() {

        // Creamos el modelo
        ModeloTablaAlmacen m = (ModeloTablaAlmacen) this.tablaAlmacenes.getModel();

        // Insertamos los datos
        m.setFilas(fa.obtenerAlmacenes());

    }

    // Funcion para cargar los datos en la tabla de empresas
    private void anhadirEmpresasVendedoras() {

        // Creamos el modelo
        ModeloTablaEmpresas m = (ModeloTablaEmpresas) this.tablaEmpresas.getModel();

        // Insertamos los datos
        m.setFilas(fa.obtenerEmpresasVendedoras());

    }

    // Funcion para gestionar el comportamiento y propiedades de los distintos elementos
    private void customBehavior() {

        // Indicamos el modo de seleccion
        this.tablaEmpresas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaAlmacenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaEmpresas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isEmpresaEditing = tablaEmpresas.getSelectedRows().length != 0;

                // Si hay alguna fila seleccionada
                if (isEmpresaEditing) {

                    // Obtenemos la fila y guardamos la empresa seleccionada
                    int row = tablaEmpresas.getSelectedRow();
                    selectedEmpresa = ((ModeloTablaEmpresas) tablaEmpresas.getModel()).obtenerEmpresa(row);

                }

            }

        });

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaAlmacenes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isAlmacenEditing = tablaAlmacenes.getSelectedRows().length != 0;

                // Si hay alguna seleccionada
                if (isAlmacenEditing) {

                    // Obtenemos la fila y guardamos el almacen seleccionada
                    int row = tablaAlmacenes.getSelectedRow();
                    selectedAlmacen = ((ModeloTablaAlmacen) tablaAlmacenes.getModel()).obtenerAlmacen(row);

                }

            }

        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionArea = new javax.swing.JTextArea();
        nombreInput = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEmpresas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAlmacenes = new javax.swing.JTable();
        existenciasSpinner = new javax.swing.JSpinner();
        precioSpinner = new javax.swing.JSpinner();
        AceptarBtn = new javax.swing.JButton();
        CancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre:");

        jLabel3.setText("Precio:");

        jLabel4.setText("Existencias: ");

        jLabel2.setText("Descripcion:");

        descripcionArea.setColumns(20);
        descripcionArea.setRows(5);
        jScrollPane1.setViewportView(descripcionArea);

        tablaEmpresas.setModel(new ModeloTablaEmpresas());
        jScrollPane2.setViewportView(tablaEmpresas);

        jLabel5.setText("Empresa vendedora:");

        jLabel6.setText("Almacén:");

        tablaAlmacenes.setModel(new ModeloTablaAlmacen());
        jScrollPane3.setViewportView(tablaAlmacenes);

        AceptarBtn.setText("Aceptar");
        AceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarBtnActionPerformed(evt);
            }
        });

        CancelarBtn.setText("Cancelar");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(nombreInput, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(531, 531, 531)))
                        .addComponent(precioSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(existenciasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CancelarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AceptarBtn)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(nombreInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(existenciasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AceptarBtn)
                    .addComponent(CancelarBtn))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelarBtnActionPerformed

    private void AceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarBtnActionPerformed

        // Obtenemos los datos necesarios de los campos de texto
        this.nombre = this.nombreInput.getText();
        this.descripcion = this.descripcionArea.getText();
        this.precio = (float) this.precioSpinner.getValue();
        this.existencias = (int) this.existenciasSpinner.getValue();

        // Si se cumple que tenemos todos los datos que necesitamos
        if (selectedEmpresa != null && selectedAlmacen != null && nombre != null && descripcion != null && precio != 0f && existencias != 0) {

            // Creamos la instancia del producto que queremos insertar
            Producto p = new Producto(-1, selectedEmpresa, selectedAlmacen, nombre, descripcion, precio, existencias);

            // LLamamos a la funcion de crear
            int res = fa.crearProducto(selectedEmpresa, selectedAlmacen, p);

            // Si la respuesta es correcta mostramos un mensaje
            if (res == 1) {

                this.fa.muestraExcepcion("Producto creado correctamente", Color.GREEN);
                this.dispose();

            }

        }

    }//GEN-LAST:event_AceptarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AceptarBtn;
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JTextArea descripcionArea;
    private javax.swing.JSpinner existenciasSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nombreInput;
    private javax.swing.JSpinner precioSpinner;
    private javax.swing.JTable tablaAlmacenes;
    private javax.swing.JTable tablaEmpresas;
    // End of variables declaration//GEN-END:variables
}
