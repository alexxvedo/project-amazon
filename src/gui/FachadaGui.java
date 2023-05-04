package gui;

import aplicacion.Cliente;
import java.awt.Color;

// Clase fachada que nos proporciona acceso a los distintos metodos y funcionalidades de la interfaz grafica
public class FachadaGui {

    aplicacion.FachadaAplicacion fa;
    VPrincipal vp;
    VCliente vc;
    VEliminarAlmacen vel;

    public FachadaGui(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
    }

    // Funcion para iniciar la vista por defecto
    public void iniciaVista() {

        VAutentificacion va;

        va  = new VAutentificacion(true, fa);
        va.setVisible(true);

    }

    // Funcion para ver la ventana principal
    public void verVentanaPrincipal(Cliente cliente) {
        this.vp = new VPrincipal(fa, cliente);
        vp.setVisible(true);
    }

    // Funcion que nos permite ver la ventana de perfil personal
    public void verVentanaPerfil(Cliente cliente) {

        vc = new VCliente(vp, true, fa, cliente);
        vc.setVisible(true);

    }

    // Funcion para que el administrador pueda gestionar almacenes
    public void verEliminarAlmacen() {
        vel = new VEliminarAlmacen(vp, true, fa);
        vel.setVisible(true);
    }

    // Funcion para ver la ventana que permite que un cliente vea su cesta de producto y realice la compra
    public void verVentanaCesta(Cliente cliente) {

        VCesta vce = new VCesta(vp, true, fa, cliente);
        vce.setVisible(true);

    }

    // Funcion para ver la ventana que permite que un cliente pueda contratar el prime
    public void verVentanaObtenerPrime(Cliente cliente) {

        VObtenerPrime vPrime = new VObtenerPrime(vp, true, fa, vc, cliente);
        vPrime.setVisible(true);

    }

    // Funcion para ver la ventana que permite que un cliente pueda gestionar sus direcciones de envio
    public void verVentanaDirecciones(Cliente cliente) {

        VDirecciones vd = new VDirecciones(vp, true, fa, vc, cliente);
        vd.setVisible(true);

    }

    // Funcion para ver la ventana que permite que un cliente pueda gestionar sus metodos de pago
    public void verVentanaMetodosPago(Cliente cliente) {

        VMetodosPago vmp = new VMetodosPago(vp, true, fa, vc, cliente);
        vmp.setVisible(true);

    }

    // Funcion para ver la ventana que permite que un administrador agregue nuevos productos
    public void verVentanaAnhadirProducto() {
        VAnhadirProducto vanha = new VAnhadirProducto(vp, true, fa);
        vanha.setVisible(true);
    }

    // Funcion generica para mostrar mensajes
    public void muestraExcepcion(String txtExcepcion, Color color) {

        VAviso va  = new VAviso(vp, true, txtExcepcion, color);
        va.setVisible(true);

    }

}
