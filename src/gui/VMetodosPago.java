package gui;

import aplicacion.Cliente;
import aplicacion.MetodoPago;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VMetodosPago extends javax.swing.JDialog {

    private Cliente cliente;
    private aplicacion.FachadaAplicacion fa;
    private boolean isEditing = false;
    private MetodoPago oldM;

    public VMetodosPago(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa, javax.swing.JDialog ventanaAnterior, Cliente cliente) {
        super(parent, modal);
        this.cliente = cliente;
        this.fa = fa;
        initComponents();
        this.obtenerMetodosPago();
        this.customBehavior();
    }

    private void customBehavior() {

        this.editBtn.setVisible(false);

        this.tablaMetodosPago.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.tablaMetodosPago.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                isEditing = tablaMetodosPago.getSelectedRows().length != 0;

                saveBtn.setVisible(!isEditing);
                editBtn.setVisible(isEditing);
                removeBtn.setVisible(isEditing);

                if (isEditing) {

                    int row = tablaMetodosPago.getSelectedRow();
                    MetodoPago m = ((ModeloTablaMetodosPago) tablaMetodosPago.getModel()).obtenerMetodoPago(row);
                    oldM = m;
                    
                    numTarText.setText("" + m.getNumeroTarjeta());
                    preferidaBtn.setSelected(m.isPreferida());
                    activaBtn.setSelected(m.isActiva());

                } else {

                    numTarText.setText("");
                    preferidaBtn.setSelected(true);

                }

            }

        });

    }

    private void obtenerMetodosPago() {

        ModeloTablaMetodosPago m = (ModeloTablaMetodosPago) this.tablaMetodosPago.getModel();

        m.setFilas(fa.obtenerMetodosPago(this.cliente));

        if (m.getRowCount() > 0) {

            this.tablaMetodosPago.setRowSelectionInterval(0, 0);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMetodosPago = new javax.swing.JTable();
        editBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        numTarLabel = new javax.swing.JLabel();
        numTarText = new javax.swing.JTextField();
        preferidaBtn = new javax.swing.JRadioButton();
        saveBtn = new javax.swing.JButton();
        activaBtn = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        closeBtn.setText("Cerrar");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        tablaMetodosPago.setModel(new ModeloTablaMetodosPago());
        jScrollPane1.setViewportView(tablaMetodosPago);

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Eliminar");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        numTarLabel.setText("Num. tar.");

        preferidaBtn.setText("Preferida");
        preferidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferidaBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Guardar");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        activaBtn.setText("Activa");
        activaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numTarLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numTarText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preferidaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(activaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(removeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeBtn)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numTarLabel)
                    .addComponent(numTarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preferidaBtn)
                    .addComponent(saveBtn)
                    .addComponent(editBtn)
                    .addComponent(activaBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeBtn)
                    .addComponent(removeBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed

        MetodoPago m = new MetodoPago(Long.parseLong(numTarText.getText()), cliente, activaBtn.isSelected(), preferidaBtn.isSelected());
        int res = this.fa.actualizarMetodoPago(oldM, m);

        if (res == 1) {

            this.fa.muestraExcepcion("Metodo actualizado correctamente", Color.GREEN);
            this.obtenerMetodosPago();

        }

    }//GEN-LAST:event_editBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere eliminar este metodo de pago?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {

            int res = this.fa.eliminarMetodoPago(oldM);

            if (res == 1) {

                this.fa.muestraExcepcion("Metodo eliminado correctamente", Color.GREEN);
                this.obtenerMetodosPago();

            }

        }

    }//GEN-LAST:event_removeBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed

        MetodoPago m = new MetodoPago(Long.parseLong(numTarText.getText()), cliente, true, preferidaBtn.isSelected());
        int res = this.fa.crearMetodoPago(m);

        if (res == 1) {

            this.fa.muestraExcepcion("Metodo creado correctamente", Color.GREEN);
            this.obtenerMetodosPago();

        }

    }//GEN-LAST:event_saveBtnActionPerformed

    private void preferidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferidaBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preferidaBtnActionPerformed

    private void activaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activaBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activaBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton activaBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numTarLabel;
    private javax.swing.JTextField numTarText;
    private javax.swing.JRadioButton preferidaBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTable tablaMetodosPago;
    // End of variables declaration//GEN-END:variables
}
