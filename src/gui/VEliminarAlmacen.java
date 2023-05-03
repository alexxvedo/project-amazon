/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import aplicacion.Almacen;
import aplicacion.FachadaAplicacion;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author alumnogreibd
 */
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
    
    private void customBehavior() {

        this.eliminarBtn.setVisible(false);

        this.tablaAlmacen1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.tablaAlmacen2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.tablaAlmacen1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                isAlmacen1Editing = tablaAlmacen1.getSelectedRows().length != 0;

                if (isAlmacen1Editing) {

                    int row = tablaAlmacen1.getSelectedRow();
                    almacenEliminar = ((ModeloTablaAlmacen) tablaAlmacen1.getModel()).obtenerAlmacen(row);

                }
                
                if(almacenEliminar != null && almacenDestino != null){
                    eliminarBtn.setVisible(true);
                }

            }

        });
        
        this.tablaAlmacen2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                isAlmacen2Editing = tablaAlmacen2.getSelectedRows().length != 0;

                if (isAlmacen2Editing) {

                    int row = tablaAlmacen2.getSelectedRow();
                    almacenDestino = ((ModeloTablaAlmacen) tablaAlmacen2.getModel()).obtenerAlmacen(row);

                }
                
                if(almacenEliminar != null && almacenDestino != null){
                    eliminarBtn.setVisible(true);
                }

            }

        });
        
        

    }

    
    @SuppressWarnings("unchecked")
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
        if(almacenEliminar.getId() != almacenDestino.getId()){
            int res = fa.eliminarAlmacen(almacenEliminar, almacenDestino);
            if(res == 1){
                fa.muestraExcepcion("Almacen eliminado", Color.green);
            }
            anhadirAlmacenes();
        }else {
            fa.muestraExcepcion("Los almacenes son los mismos", Color.red);
        }
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirBtnActionPerformed

    private void anhadirAlmacenes() {

        ModeloTablaAlmacen m1 = (ModeloTablaAlmacen) this.tablaAlmacen1.getModel();
        ModeloTablaAlmacen m2 = (ModeloTablaAlmacen) this.tablaAlmacen2.getModel();

        ArrayList<Almacen> almacenes = this.fa.obtenerAlmacenes();
        
        m1.setFilas(almacenes);
        m2.setFilas(almacenes);

        if (m1.getRowCount() > 0) {
            
            this.tablaAlmacen1.setRowSelectionInterval(0, 0);
            this.tablaAlmacen2.setRowSelectionInterval(0, 0);

        }

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
