package gui;

import aplicacion.Producto;
import aplicacion.Cliente;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VPrincipal extends javax.swing.JFrame {

    aplicacion.FachadaAplicacion fa;

    private boolean isEditing;
    private Producto selectedProd;
    private int cantidadProductoActual = 0;
    private Cliente clienteActual;

    public VPrincipal(aplicacion.FachadaAplicacion fa, Cliente cliente) {
        this.fa = fa;
        this.clienteActual = cliente;

        initComponents();
        customBehavior();
    }

    private void buscarProductos() {

        ModeloTablaProductos m = (ModeloTablaProductos) this.tablaProductos.getModel();

        m.setFilas(fa.obtenerProductos(searchText.getText()));

        if (m.getRowCount() > 0) {

            this.tablaProductos.setRowSelectionInterval(0, 0);

        }

    }

    private void customBehavior() {

        anhadirCestaBtn.setVisible(false);
        cantidadSpinner.setVisible(false);

        if (clienteActual.getEmail().equals("admin")) {
            anhadirProductoBtn.setVisible(true);
        } else {
            anhadirProductoBtn.setVisible(false);
        }

        this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                isEditing = tablaProductos.getSelectedRows().length != 0;

                anhadirCestaBtn.setVisible(isEditing);
                cantidadSpinner.setVisible(isEditing);

                if (isEditing) {

                    int row = tablaProductos.getSelectedRow();
                    Producto p = ((ModeloTablaProductos) tablaProductos.getModel()).obtenerProducto(row);

                    selectedProd = p;
                    cantidadSpinner.setModel(new SpinnerNumberModel(1, 1, p.getExistencias(), 1));

                }

            }

        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchLabel = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        VerCestaBtn = new javax.swing.JButton();
        verPerfilBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        anhadirCestaBtn = new javax.swing.JButton();
        cantidadSpinner = new javax.swing.JSpinner();
        anhadirProductoBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Amazon");
        setName("vPrincipal"); // NOI18N
        setResizable(false);

        searchLabel.setText("Buscador");

        searchBtn.setText("Buscar");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Salir");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        VerCestaBtn.setText("Ver cesta");
        VerCestaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerCestaBtnActionPerformed(evt);
            }
        });

        verPerfilBtn.setText("Ver perfil");
        verPerfilBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPerfilBtnActionPerformed(evt);
            }
        });

        tablaProductos.setModel(new ModeloTablaProductos());
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaProductos);

        anhadirCestaBtn.setText("Añadir a cesta");
        anhadirCestaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anhadirCestaBtnActionPerformed(evt);
            }
        });

        anhadirProductoBtn.setText("Añadir producto");
        anhadirProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anhadirProductoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchText)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(VerCestaBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verPerfilBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(anhadirProductoBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anhadirCestaBtn)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exitBtn)
                            .addComponent(searchBtn)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitBtn)
                    .addComponent(VerCestaBtn)
                    .addComponent(verPerfilBtn)
                    .addComponent(anhadirCestaBtn)
                    .addComponent(cantidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anhadirProductoBtn))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Biblioteca Informática");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed

        this.buscarProductos();

    }//GEN-LAST:event_searchBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void verPerfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verPerfilBtnActionPerformed
        this.fa.verPerfil();
    }//GEN-LAST:event_verPerfilBtnActionPerformed

    private void anhadirCestaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anhadirCestaBtnActionPerformed
        this.cantidadProductoActual = (int) this.cantidadSpinner.getValue();
        if (cantidadProductoActual <= 0) {
            return;
        }
        this.fa.insertarProductoCesta(this.selectedProd, this.cantidadProductoActual, false);
    }//GEN-LAST:event_anhadirCestaBtnActionPerformed

    private void VerCestaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerCestaBtnActionPerformed
        this.fa.verVentanaCesta();
    }//GEN-LAST:event_VerCestaBtnActionPerformed

    private void anhadirProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anhadirProductoBtnActionPerformed
        fa.verVentanaAnhadirProductos();
    }//GEN-LAST:event_anhadirProductoBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VerCestaBtn;
    private javax.swing.JButton anhadirCestaBtn;
    private javax.swing.JButton anhadirProductoBtn;
    private javax.swing.JSpinner cantidadSpinner;
    private javax.swing.JButton exitBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JButton verPerfilBtn;
    // End of variables declaration//GEN-END:variables
}
