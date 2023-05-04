package aplicacion;

import java.util.ArrayList;
import baseDatos.FachadaBaseDatos;

// Clase que actua de intermediaria para concentrar las funciones relacionadas con distribuidores
public class GestionDistribuidor {

    FachadaBaseDatos fbd;

    public GestionDistribuidor(FachadaBaseDatos fbd) {
        this.fbd = fbd;
    }

    // Funcion que nos permite obtener el listado de distribuidores que trabajan con Amazon
    public ArrayList<Distribuidor> obtenerDistribuidores() {
        return fbd.obtenerDistribuidores();
    }

}
