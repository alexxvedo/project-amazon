package gui;

import aplicacion.Cliente;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class VCliente extends javax.swing.JDialog {

    private Cliente cliente;
    private boolean activeEdit = true;
    private aplicacion.FachadaAplicacion fa;

    public VCliente(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa, Cliente cliente) {
        super(parent, modal);
        this.cliente = cliente;
        this.fa = fa;
        initComponents();
        changeEditStatus();
        obtenerPedidos();
    }

    private void obtenerPedidos() {

        ModeloTablaPedidos m = (ModeloTablaPedidos) this.tablaPedidos.getModel();

        m.setFilas(fa.obtenerPedidos(cliente));

        if (m.getRowCount() > 0) {

            this.tablaPedidos.setRowSelectionInterval(0, 0);

        }

    }

    private void changeEditStatus() {

        this.getPrimeBtn.setVisible(!this.cliente.isPrime());

        this.activeEdit = !this.activeEdit;

        // Mostramos los elementos ocultos
        this.passwordLabel.setVisible(this.activeEdit);
        this.passwordText.setVisible(this.activeEdit);
        this.saveEditBtn.setVisible(this.activeEdit);
        this.cancelEditBtn.setVisible(this.activeEdit);
        this.eliminarClienteBtn.setVisible(this.activeEdit);

        this.editBtn.setVisible(!this.activeEdit);

        // Habilitamos la edicion de los demas
        this.nameText.setEnabled(this.activeEdit);
        this.fecNacimientoText.setEnabled(this.activeEdit);
        this.telefonoText.setEnabled(this.activeEdit);
        this.emailText.setEnabled(this.activeEdit);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jDialog1 = new javax.swing.JDialog();
        editBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        nameText = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        telefonoLabel = new javax.swing.JLabel();
        telefonoText = new javax.swing.JTextField();
        fecNacimientoLabel = new javax.swing.JLabel();
        fecNacimientoText = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        getPrimeBtn = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();
        saveEditBtn = new javax.swing.JButton();
        cancelEditBtn = new javax.swing.JButton();
        verDireccionesBtn = new javax.swing.JButton();
        verMetodosPagoBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        eliminarClienteBtn = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Area personal");

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Cerrar");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        nameText.setText(this.cliente.getNombre());
        nameText.setToolTipText("");
        nameText.setEnabled(false);

        nombreLabel.setText("Nombre");

        telefonoLabel.setText("Telefono");

        telefonoText.setText(this.cliente.getTelefonoString());
        telefonoText.setEnabled(false);

        fecNacimientoLabel.setText("F. Nac.");

        fecNacimientoText.setText(this.cliente.getFechaNacimientoString());
        fecNacimientoText.setEnabled(false);

        emailLabel.setText("Email");

        emailText.setText(this.cliente.getEmail());
        emailText.setEnabled(false);

        passwordLabel.setText("Pass.");

        getPrimeBtn.setText("Obtener prime");
        getPrimeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPrimeBtnActionPerformed(evt);
            }
        });

        saveEditBtn.setText("Guardar");
        saveEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveEditBtnActionPerformed(evt);
            }
        });

        cancelEditBtn.setText("Cancelar");
        cancelEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEditBtnActionPerformed(evt);
            }
        });

        verDireccionesBtn.setText("Ver dir.");
        verDireccionesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verDireccionesBtnActionPerformed(evt);
            }
        });

        verMetodosPagoBtn.setText("Ver tarje.");
        verMetodosPagoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verMetodosPagoBtnActionPerformed(evt);
            }
        });

        tablaPedidos.setModel(new ModeloTablaPedidos());
        jScrollPane1.setViewportView(tablaPedidos);

        eliminarClienteBtn.setText("Eliminar");
        eliminarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarClienteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fecNacimientoLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fecNacimientoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(nombreLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nameText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(telefonoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telefonoText, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(emailLabel)
                                .addGap(23, 23, 23)
                                .addComponent(emailText)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eliminarClienteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelEditBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveEditBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(getPrimeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verDireccionesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verMetodosPagoBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreLabel)
                    .addComponent(telefonoLabel)
                    .addComponent(telefonoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveEditBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecNacimientoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecNacimientoLabel)
                    .addComponent(emailLabel)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelEditBtn))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarClienteBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitBtn)
                            .addComponent(editBtn)
                            .addComponent(verMetodosPagoBtn)
                            .addComponent(verDireccionesBtn)
                            .addComponent(getPrimeBtn))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        this.changeEditStatus();
    }//GEN-LAST:event_editBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void getPrimeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPrimeBtnActionPerformed
        this.fa.verObtenerPrime();
    }//GEN-LAST:event_getPrimeBtnActionPerformed

    private void verDireccionesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verDireccionesBtnActionPerformed
        this.fa.verDirecciones();
    }//GEN-LAST:event_verDireccionesBtnActionPerformed

    private void verMetodosPagoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verMetodosPagoBtnActionPerformed
        this.fa.verMetodosPago();
    }//GEN-LAST:event_verMetodosPagoBtnActionPerformed

    private void saveEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveEditBtnActionPerformed

        try {

            int telefono = Integer.parseInt(this.telefonoText.getText());
            SimpleDateFormat formatter = new SimpleDateFormat(Cliente.format);
            Date fechaNacimiento = formatter.parse(this.fecNacimientoText.getText());
            String password = this.cliente.getContrasena();

            if (this.passwordText.getPassword().length != 0) {

                password = this.passwordText.getPassword().toString();

            }

            Cliente cliente = new Cliente(this.cliente.getId(), this.nameText.getText(), telefono, fechaNacimiento, this.cliente.isPrime(), this.emailText.getText(), password);

            int res = this.fa.actualizarCliente(cliente);

            if (res == 1) {

                this.fa.muestraExcepcion("Cliente actualizado correctamente", Color.GREEN);
                this.cliente = cliente;
                this.changeEditStatus();

            }

        } catch (Exception e) {

            this.fa.muestraExcepcion(e.getMessage(), Color.RED);

        }

    }//GEN-LAST:event_saveEditBtnActionPerformed

    private void cancelEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditBtnActionPerformed
        this.changeEditStatus();
    }//GEN-LAST:event_cancelEditBtnActionPerformed

    private void eliminarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarClienteBtnActionPerformed

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere eliminar su perfil?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {

            int res = this.fa.eliminarCliente(cliente);

            if (res == 1) {

                this.fa.muestraExcepcion("Cliente eliminado correctamente", Color.GREEN);
                this.dispose();
                System.exit(0);

            }

        }

    }//GEN-LAST:event_eliminarClienteBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelEditBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton eliminarClienteBtn;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailText;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel fecNacimientoLabel;
    private javax.swing.JTextField fecNacimientoText;
    private javax.swing.JButton getPrimeBtn;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameText;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JButton saveEditBtn;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JTextField telefonoText;
    private javax.swing.JButton verDireccionesBtn;
    private javax.swing.JButton verMetodosPagoBtn;
    // End of variables declaration//GEN-END:variables
}
