package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con cliente
public class GestionClientes {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    // Se guarda la instancia de cliente en el gestor para proporcionarla donde sea necesario
    Cliente cliente;

    public GestionClientes(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    // Funcinon para ver la venta de perfil de un cliente
    public void verVentanaPerfil(Cliente c) {
        fgui.verVentanaPerfil(c);
    }

    // Funcion para ver la ventana para obtener el prime
    public void verVentanaObtenerPrime(Cliente c) {
        fgui.verVentanaObtenerPrime(c);
    }

    // Funcion que nos permite iniciar sesion
    public Boolean comprobarAutentificacion(String email, String password) {
        this.cliente = fbd.validarUsuario(email, password);
        return this.cliente != null;
    }

    // Funcion que permite que un cliente se registre
    public int crearCliente(Cliente c) {
        return fbd.crearCliente(c);
    }

    // Funcion que permite a un cliente pueda actualizar sus datos
    public int actualizarCliente(Cliente c) {

        // Comprobamos que nos devuelve la funcion de actualizacion
        int res = fbd.actualizarCliente(c);

        // Si la respuesta es positiva, la accion se completo correctamente actualizamos la instancia que tenemos almacenada
        if (res == 1) {
            this.cliente = c;
        }

        // Devolvemos la respuesta
        return res;

    }

    // Funcion que permite que cualquier cliente pueda borrar su cuenta
    public int eliminarCliente(Cliente c) {

        // Comprobamos que nos devuelve la funcion de actualizacion
        int res = fbd.eliminarCliente(c);

        // Si la respuesta es positiva, la accion se completo correctamente anulamos la instancia actual
        if (res == 1) {
            this.cliente = null;
        }

        return res;

    }

    public Cliente getCliente() {
        return cliente;
    }

}
