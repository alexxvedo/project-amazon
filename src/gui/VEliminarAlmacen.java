package gui;

import aplicacion.Almacen;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VEliminarAlmacen extends javax.swing.JDialog {

    aplicacion.FachadaAplicacion fa;
    boolean isAlmacen1Editing = false, isAlmacen2Editing = false;
    Almacen almacenEliminar, almacenDestino;

    public VEliminarAlmacen(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa) {

        super(parent, modal);
        this.fa = fa;
        initComponents();
        anhadirAlmacenes();
        customBehavior();

    }

    // Funcion para gestionar el comportamiento y propiedades de los distintos elementos
    private void customBehavior() {

        // Ocultamos el boton hasta que se cumplan las condiciones para que se pueda usar
        this.eliminarBtn.setVisible(false);

        // Indicamos el modo de seleccion de la tabla de direcciones
        this.tablaAlmacen1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaAlmacen2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaAlmacen1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isAlmacen1Editing = tablaAlmacen1.getSelectedRows().length != 0;

                // Si tenemos alguna fila seleccionada
                if (isAlmacen1Editing) {

                    // Obtenemos la fila
                    int row = tablaAlmacen1.getSelectedRow();

                    // Recuperamos el valor de la fila
                    almacenEliminar = ((ModeloTablaAlmacen) tablaAlmacen1.getModel()).obtenerAlmacen(row);

                }

                // Ajustamos la visibilidad de los botonos cuando se tengan 2 almacenes seleccionados
                if (almacenEliminar != null && almacenDestino != null) {

                    eliminarBtn.setVisible(true);

                }

            }

        });

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaAlmacen2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isAlmacen2Editing = tablaAlmacen2.getSelectedRows().length != 0;

                // Si tenemos alguna fila seleccionada
                if (isAlmacen2Editing) {

                    // Obtenemos la fila
                    int row = tablaAlmacen2.getSelectedRow();

                    // Recuperamos el valor de la fila
                    almacenDestino = ((ModeloTablaAlmacen) tablaAlmacen2.getModel()).obtenerAlmacen(row);

                }

                // Ajustamos la visibilidad de los botonos cuando se tengan 2 almacenes seleccionados
                if (almacenEliminar != null && almacenDestino != null) {
                    eliminarBtn.setVisible(true);
                }

            }

        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlmacen1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAlmacen2 = new javax.swing.JTable();
        salirBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Almacen a eliminar:");

        tablaAlmacen1.setModel(new ModeloTablaAlmacen());
        jScrollPane1.setViewportView(tablaAlmacen1);

        jLabel2.setText("Almacen destino: ");

        tablaAlmacen2.setModel(new ModeloTablaAlmacen());
        jScrollPane3.setViewportView(tablaAlmacen2);

        salirBtn.setText("Salit");
        salirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salirBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eliminarBtn)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salirBtn)
                    .addComponent(eliminarBtn))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed

        // Comprobamos que tenemos seleccionados 2 almacenes distintos
        if (almacenEliminar.getId() != almacenDestino.getId()) {

            // Llamamos a la funcion para eliminar el almacen indicando moviendo los productos a otro almacen disponible
            int res = fa.eliminarAlmacen(almacenEliminar, almacenDestino);

            // Si la accion se realizo correctamente, mostramos un mensaje
            if (res == 1) {

                fa.muestraExcepcion("Almacen eliminado", Color.green);

            }

            // Recargamos los datos de las tablas
            anhadirAlmacenes();

        }

    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirBtnActionPerformed

    // Funcion para cargar las filas de las tablas de almacenes
    private void anhadirAlmacenes() {

        // Creamos los modelos
        ModeloTablaAlmacen m1 = (ModeloTablaAlmacen) this.tablaAlmacen1.getModel();
        ModeloTablaAlmacen m2 = (ModeloTablaAlmacen) this.tablaAlmacen2.getModel();

        // Obtenemos los almacenes almacenados en la base de datos
        ArrayList<Almacen> almacenes = this.fa.obtenerAlmacenes();

        // Insertamos los datos en las tablas
        m1.setFilas(almacenes);
        m2.setFilas(almacenes);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton salirBtn;
    private javax.swing.JTable tablaAlmacen1;
    private javax.swing.JTable tablaAlmacen2;
    // End of variables declaration//GEN-END:variables
}
