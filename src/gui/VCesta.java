/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import aplicacion.Cliente;
import java.util.*;
import aplicacion.MetodoPago;
import aplicacion.Producto;
import aplicacion.Direccion;
import aplicacion.Distribuidor;
import aplicacion.FachadaAplicacion;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author alumnogreibd
 */
public class VCesta extends javax.swing.JFrame {
    
    private HashMap<Producto, Integer> productos = new HashMap<>();
    private Cliente cliente  = null;
    private MetodoPago metodoPago = null;
    private aplicacion.FachadaAplicacion fa = null;
    private boolean isProductEditing;
    private boolean isMetodoPagoEditing;
    private boolean isDireccionEditing;
    private Producto selectedProd;
    private MetodoPago selectedMetodoPago;
    private Direccion selectedDir;
    
    private Float totalPagar = 0f;

    
    /**
     * Creates new form VCesta
     */
    public VCesta(aplicacion.FachadaAplicacion fa, HashMap<Producto, Integer> productos, Cliente cliente) {
        this.productos = productos;
        this.cliente = cliente;
        this.fa = fa;
        initComponents();
        anhadirProductos();
        anhadirMetodosPago();
        anhadirDirecciones();
        customBehavior();
    }
    
    
    
    
    private void anhadirProductos() {

        ModeloTablaProductosCesta m = (ModeloTablaProductosCesta) this.tablaProductosCesta.getModel();
        ArrayList<Producto> productosList = new ArrayList<>();
        ArrayList<Integer> cantidades = new ArrayList<>();
        for (Producto prod : this.productos.keySet()){
            productosList.add(prod);
            cantidades.add(productos.get(prod));
            totalPagar += prod.getPrecio() * productos.get(prod);
        }
        m.setFilas(productosList, cantidades);

        if (m.getRowCount() > 0) {

            this.tablaProductosCesta.setRowSelectionInterval(0, 0);

        }

    }
     
    private void anhadirMetodosPago() {
        ModeloTablaMetodosPago m = (ModeloTablaMetodosPago) this.tablaMetodosPagoCesta.getModel();
        
        
        m.setFilas(fa.obtenerMetodosPago(this.cliente));

        if (m.getRowCount() > 0) {
            this.tablaMetodosPagoCesta.setRowSelectionInterval(0, 0);
        }
       
    }
    
    
    private void anhadirDirecciones() {
        ModeloTablaDirecciones m = (ModeloTablaDirecciones) this.tablaDireccionesCesta.getModel();
        
        m.setFilas(fa.obtenerDirecciones(this.cliente));
        
        if(m.getRowCount() > 0){
            this.tablaDireccionesCesta.setRowSelectionInterval(0,0);
        }
        
    }
    
    private void customBehavior() {


        this.tablaProductosCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaDireccionesCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaMetodosPagoCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.TotalPagarLabel.setText(totalPagar.toString());
        
        
        this.tablaProductosCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                isProductEditing = tablaProductosCesta.getSelectedRows().length != 0;
                
                if(isProductEditing){
                    int row = tablaProductosCesta.getSelectedRow();
                    selectedProd = ((ModeloTablaProductosCesta) tablaProductosCesta.getModel()).obtenerProducto(row);
                }
            }
        
        
        
        });
        
        this.tablaMetodosPagoCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                isMetodoPagoEditing = tablaMetodosPagoCesta.getSelectedRows().length != 0;
                
                if(isMetodoPagoEditing){
                    int row = tablaMetodosPagoCesta.getSelectedRow();
                    selectedMetodoPago = ((ModeloTablaMetodosPago) tablaMetodosPagoCesta.getModel()).obtenerMetodoPago(row);
                }
            }
        
        
        
        });
        
        this.tablaDireccionesCesta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                isDireccionEditing = tablaDireccionesCesta.getSelectedRows().length != 0;
                
                if(isDireccionEditing){
                    int row = tablaDireccionesCesta.getSelectedRow();
                    selectedDir = ((ModeloTablaDirecciones) tablaDireccionesCesta.getModel()).obtenerDireccion(row);
                }
            }
        });
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EliminarBtn = new javax.swing.JButton();
        PagarBtn = new javax.swing.JButton();
        CancelarBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMetodosPagoCesta = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductosCesta = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDireccionesCesta = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        TotalPagarLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Productos:");

        jLabel2.setText("Metodo de pago:");

        EliminarBtn.setText("Eliminar");
        EliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBtnActionPerformed(evt);
            }
        });

        PagarBtn.setText("Pagar");
        PagarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PagarBtnActionPerformed(evt);
            }
        });

        CancelarBtn.setText("Cancelar");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        tablaMetodosPagoCesta.setModel(new ModeloTablaMetodosPago());
        jScrollPane3.setViewportView(tablaMetodosPagoCesta);

        tablaProductosCesta.setModel(new ModeloTablaProductosCesta());
        jScrollPane1.setViewportView(tablaProductosCesta);

        jLabel3.setText("Direcciones:");

        tablaDireccionesCesta.setModel(new ModeloTablaDirecciones());
        jScrollPane2.setViewportView(tablaDireccionesCesta);

        jLabel4.setText("TOTAL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(946, 946, 946))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EliminarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TotalPagarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(CancelarBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(PagarBtn)))
                                .addGap(2, 2, 2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(TotalPagarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EliminarBtn)
                    .addComponent(PagarBtn)
                    .addComponent(CancelarBtn))
                .addGap(16, 16, 16))
        );

        jScrollPane1.getAccessibleContext().setAccessibleParent(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarBtnActionPerformed

    private void PagarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PagarBtnActionPerformed
        
        
        
        if(selectedMetodoPago == null && selectedDir == null){
            ArrayList<MetodoPago> metodosPago = new ArrayList<>();
            metodosPago = fa.obtenerMetodosPago(this.cliente);
            
            ArrayList<Direccion> direcciones = new ArrayList<>();
            direcciones = fa.obtenerDirecciones(this.cliente);
            
            
            
            for (MetodoPago metodo : metodosPago){
                if(metodo.isPreferida()) selectedMetodoPago = metodo;
            }
            for (Direccion direccion : direcciones){
                if(direccion.isPreferida()) selectedDir = direccion;
            }
            
        }
        
        ArrayList<Distribuidor> distribuidores = new ArrayList<>();
        distribuidores = fa.obtenerDistribuidores();
        
        System.out.println(distribuidores.toString());
        
        Random random = new Random();
        int randomNumber = random.nextInt(distribuidores.size());
        
        ArrayList<Producto> productosList = new ArrayList<>();
        for (Producto prod : this.productos.keySet()){
            productosList.add(prod);
        }
        
        fa.crearPedido(this.cliente, this.selectedMetodoPago, this.selectedDir, distribuidores.get(randomNumber), productosList, false);
        this.dispose();
    }//GEN-LAST:event_PagarBtnActionPerformed

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JButton EliminarBtn;
    private javax.swing.JButton PagarBtn;
    private javax.swing.JLabel TotalPagarLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaDireccionesCesta;
    private javax.swing.JTable tablaMetodosPagoCesta;
    private javax.swing.JTable tablaProductosCesta;
    // End of variables declaration//GEN-END:variables
}
