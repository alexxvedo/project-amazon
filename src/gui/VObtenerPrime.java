package gui;

import aplicacion.Cliente;
import aplicacion.MetodoPago;
import aplicacion.Producto;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VObtenerPrime extends javax.swing.JDialog {

    private Cliente cliente;
    private Producto prime;
    private String primeName = "Amazon Prime";
    private aplicacion.FachadaAplicacion fa;
    private javax.swing.JDialog ventanaAnterior;

    public VObtenerPrime(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa, javax.swing.JDialog ventanaAnterior, Cliente cliente) {

        super(parent, modal);
        this.cliente = cliente;
        this.ventanaAnterior = ventanaAnterior;
        this.fa = fa;
        initComponents();
        this.obtenerMetodosPago();
        this.customBehavior();

    }

    // Funcion para gestionar el comportamiento y propiedades de los distintos elementos
    private void customBehavior() {

        // Indicamos el modo de seleccion de la tabla de direcciones
        this.tablaMetodosPago.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ocultamos el boton hasta que se cumplan las condiciones para que se pueda usar
        this.buyBtn.setVisible(false);

        // Controlamos el evento de cuando se selecciona en la tabla
        this.tablaMetodosPago.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                // Obtenemos si hay alguna fila seleccionada
                boolean isValid = tablaMetodosPago.getSelectedRows().length != 0;

                // Si hay alguna fila seleccionada
                if (!isValid) {

                    // Ocultamos el boton el boton
                    buyBtn.setVisible(isValid);
                    return;

                }

                // Obtenemos la fila
                int row = tablaMetodosPago.getSelectedRow();

                // Obtenemos el contenido de la fila, metodo de pago indicado
                MetodoPago m = ((ModeloTablaMetodosPago) tablaMetodosPago.getModel()).obtenerMetodoPago(row);

                // Mostramos el boton de pago si la tarjeta esta activa
                buyBtn.setVisible(m.isActiva());

            }

        });

    }

    // Funcion para cargar los datos en la tabla de metodos de pago
    private void obtenerMetodosPago() {

        // Creamos el modelo
        ModeloTablaMetodosPago m = (ModeloTablaMetodosPago) this.tablaMetodosPago.getModel();

        // Insertamos los datos en la tabla
        m.setFilas(fa.obtenerMetodosPago(this.cliente));

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cerrarBtn = new javax.swing.JButton();
        buyBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMetodosPago = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel3.setText("Envios gratis");

        jLabel4.setText("Envios gratis");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cerrarBtn.setText("Cerrar");
        cerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarBtnActionPerformed(evt);
            }
        });

        buyBtn.setText("Comprar");
        buyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Compra amazon Prime y disfruta de todas sus ventajas");

        tablaMetodosPago.setModel(new ModeloTablaMetodosPago());
        jScrollPane1.setViewportView(tablaMetodosPago);

        jLabel2.setText("Envios gratis");

        jLabel5.setText("Ofertas exclusivas");

        jLabel6.setText("Productos unicos");

        jLabel7.setText("Servicios personalizados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buyBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cerrarBtn)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cerrarBtn)
                    .addComponent(buyBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cerrarBtnActionPerformed

    private void buyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyBtnActionPerformed

        // Obtenemos la fila seleccionada
        int row = tablaMetodosPago.getSelectedRow();

        // Obtenemos el contenido de la fila
        MetodoPago m = ((ModeloTablaMetodosPago) tablaMetodosPago.getModel()).obtenerMetodoPago(row);

        // Creamos una nueva cesta de productos unicamente con el servicio de prime
        HashMap<Producto, Integer> productos = new HashMap<>();

        // Recuperamos los datos de prime de los productos de la base de datos
        productos.put(fa.obtenerProductos("Amazon Prime").get(0), 1);

        // Llamamos a la funcion para crear el pedido en la base de datos
        int res = this.fa.crearPedido(cliente, m, null, null, productos, true);

        // Si la accion se realizo correctamente
        if (res == 1) {

            // Creamos una nueva instancia del cliente actual con el prime a true
            Cliente nuevoCliente = new Cliente(this.cliente.getId(), this.cliente.getNombre(), this.cliente.getTelefono(), this.cliente.getFechaNacimiento(), true, this.cliente.getEmail(), this.cliente.getContrasena());

            // Llamamos a la funcion para actualizar el cliente
            res = this.fa.actualizarCliente(nuevoCliente);

            // Si todo se realizo correctamente, mostramos un mensaje y cerramos las ventanas
            if (res == 1) {

                this.fa.muestraExcepcion("Prime obtenido correctamente", Color.GREEN);
                this.dispose();
                this.ventanaAnterior.dispose();

            }

        }

    }//GEN-LAST:event_buyBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyBtn;
    private javax.swing.JButton cerrarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaMetodosPago;
    // End of variables declaration//GEN-END:variables
}
