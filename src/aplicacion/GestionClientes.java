package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

public class GestionClientes {

    FachadaGui fgui;
    FachadaBaseDatos fbd;
    Cliente cliente;

    public GestionClientes(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void verVentanaPerfil(Cliente c) {
        fgui.verVentanaPerfil(c);
    }

    public void verVentanaObtenerPrime(Cliente c) {
        fgui.verVentanaObtenerPrime(c);
    }

    public Boolean comprobarAutentificacion(String email, String password) {
        this.cliente = fbd.validarUsuario(email, password);
        return this.cliente != null;
    }

    public int actualizarCliente(Cliente c) {
        int res = fbd.actualizarCliente(c);
        if (res == 1) {
            this.cliente = c;
        }
        return res;
    }

    public int eliminarCliente(Cliente c) {
        int res = fbd.eliminarCliente(c);
        if (res == 1) {
            this.cliente = null;
        }
        return res;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
