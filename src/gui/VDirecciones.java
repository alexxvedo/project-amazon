package gui;

import aplicacion.Cliente;
import aplicacion.Direccion;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VDirecciones extends javax.swing.JDialog {

    private Cliente cliente;
    private aplicacion.FachadaAplicacion fa;
    private boolean isEditing;
    private Direccion selectedDir;

    public VDirecciones(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa, javax.swing.JDialog ventanaAnterior, Cliente cliente) {
        super(parent, modal);
        this.cliente = cliente;
        this.fa = fa;
        initComponents();
        this.obtenerDirecciones();
        this.customBehavior();
    }

    // Funcion para cargar los datos en la tabla de direcciones
    private void obtenerDirecciones() {

        // Creamos el modelo
        ModeloTablaDirecciones m = (ModeloTablaDirecciones) this.tablaDirecciones.getModel();

        // Insertamos los datos en la tabla
        m.setFilas(fa.obtenerDirecciones(this.cliente));

    }

    // Funcion para gestionar el comportamiento y propiedades de los distintos elementos
    private void customBehavior() {

        // Ocultamos el boton hasta que se cumplan las condiciones para que se pueda usar
        this.editBtn.setVisible(false);

        // Indicamos el modo de seleccion de la tabla de direcciones
        this.tablaDirecciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaDirecciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                isEditing = tablaDirecciones.getSelectedRows().length != 0;

                // Ajustamos la visibilidad de los botonos segun se tenga seleccionada alguna fila o no
                saveBtn.setVisible(!isEditing);
                editBtn.setVisible(isEditing);
                removeBtn.setVisible(isEditing);

                // Si hay alguna fila seleccionada
                if (isEditing) {

                    // Obtenemos la fila
                    int row = tablaDirecciones.getSelectedRow();

                    // Obtenemos el contenido de la fila, en este caso la direccion
                    Direccion d = ((ModeloTablaDirecciones) tablaDirecciones.getModel()).obtenerDireccion(row);

                    // Guardamos la seleccion
                    selectedDir = d;

                    // Ajustamos el contenido de los campos de texto
                    calleText.setText(d.getCalle());
                    ciudadText.setText(d.getCiudad());
                    numeroText.setText("" + d.getNumero());
                    preferidaBtn.setSelected(d.isPreferida());
                    codigoPostalText.setText("" + d.getCodigoPostal());

                } else {

                    // Ajustamos el contenido de los campos de texto
                    calleText.setText("");
                    ciudadText.setText("");
                    numeroText.setText("");
                    preferidaBtn.setSelected(true);
                    codigoPostalText.setText("");

                }

            }

        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDirecciones = new javax.swing.JTable();
        removeBtn = new javax.swing.JButton();
        calleLabel = new javax.swing.JLabel();
        calleText = new javax.swing.JTextField();
        numeroLabel = new javax.swing.JLabel();
        numeroText = new javax.swing.JTextField();
        ciudadLabel = new javax.swing.JLabel();
        ciudadText = new javax.swing.JTextField();
        codigoPostalLabel = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        preferidaBtn = new javax.swing.JRadioButton();
        codigoPostalText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        closeBtn.setText("Cerrar");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        tablaDirecciones.setModel(new ModeloTablaDirecciones());
        jScrollPane1.setViewportView(tablaDirecciones);

        removeBtn.setText("Eliminar");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        calleLabel.setText("Calle");

        numeroLabel.setText("Numero");

        ciudadLabel.setText("Ciudad");

        codigoPostalLabel.setText("Cod. postal");

        saveBtn.setText("Guardar");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        preferidaBtn.setText("Preferida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(removeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(calleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calleText, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ciudadLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ciudadText, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numeroLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numeroText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preferidaBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoPostalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoPostalText)))
                        .addGap(127, 127, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calleLabel)
                    .addComponent(calleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudadLabel)
                    .addComponent(ciudadText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroLabel)
                    .addComponent(numeroText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preferidaBtn)
                    .addComponent(editBtn)
                    .addComponent(codigoPostalLabel)
                    .addComponent(codigoPostalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
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

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed

        // Mostramos un mensaje solicitando confirmacion para realizar la accion
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere eliminar esta direccion?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        // Si nos indica que si, borramos la direccion de envio
        if (confirmacion == JOptionPane.YES_OPTION) {

            // LLamamos a la funcion para eliminar la direccion de envio de la base de datos
            int res = this.fa.eliminarDireccion(this.selectedDir);

            // Si la accion se realizo correctamente, mostramos un mensaje de exito
            if (res == 1) {

                this.fa.muestraExcepcion("Direccion eliminada correctamente", Color.GREEN);
                this.obtenerDirecciones();

            }

        }

    }//GEN-LAST:event_removeBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed

        // Creamos una nueva instancia de la direccion de envio
        Direccion d = new Direccion(-1, this.cliente, calleText.getText(), Integer.parseInt(numeroText.getText()), ciudadText.getText(), Integer.parseInt(codigoPostalText.getText()), preferidaBtn.isSelected());

        // Llamamos a la funcion para crear la nueva direccion en la base de datos
        int res = this.fa.crearDireccion(d);

        // Si la accion se realizo correctamente, mostramos un mensaje de exito
        if (res == 1) {

            this.fa.muestraExcepcion("Direccion creada correctamente", Color.GREEN);
            this.obtenerDirecciones();

        }

    }//GEN-LAST:event_saveBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed

        // Creamos una nueva instancia de la direccion de envio, con los datos actualizados
        Direccion d = new Direccion(selectedDir.getId(), this.cliente, calleText.getText(), Integer.parseInt(numeroText.getText()), ciudadText.getText(), Integer.parseInt(codigoPostalText.getText()), preferidaBtn.isSelected());

        // Llamamos a la funcion para actualizar la direccion en la base de datos
        int res = this.fa.actualizarDireccion(d);

        // Si la accion se realizo correctamente, mostramos un mensaje de exito
        if (res == 1) {

            this.fa.muestraExcepcion("Direccion actualizada correctamente", Color.GREEN);
            this.obtenerDirecciones();

        }

    }//GEN-LAST:event_editBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel calleLabel;
    private javax.swing.JTextField calleText;
    private javax.swing.JLabel ciudadLabel;
    private javax.swing.JTextField ciudadText;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel codigoPostalLabel;
    private javax.swing.JTextField codigoPostalText;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numeroLabel;
    private javax.swing.JTextField numeroText;
    private javax.swing.JRadioButton preferidaBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTable tablaDirecciones;
    // End of variables declaration//GEN-END:variables
}
