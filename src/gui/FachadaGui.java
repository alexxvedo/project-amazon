package gui;

import aplicacion.Cliente;
import java.awt.Color;

public class FachadaGui {

    aplicacion.FachadaAplicacion fa;
    VPrincipal vp;

    public FachadaGui(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
        this.vp = new VPrincipal(fa);
    }

    public void iniciaVista() {
        VAutentificacion va;

        va  = new VAutentificacion(vp, true, fa);
        vp.setVisible(true);
        va.setVisible(true);

    }

    public void verPerfil(Cliente cliente) {

        VCliente vc = new VCliente(vp, true, fa, cliente);
        vc.setVisible(true);

    }

    public void muestraExcepcion(String txtExcepcion, Color color) {

        VAviso va  = new VAviso(vp, true, txtExcepcion, color);
        va.setVisible(true);

    }

}
