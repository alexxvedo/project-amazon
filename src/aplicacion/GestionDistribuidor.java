package aplicacion;

import java.util.ArrayList;
import baseDatos.FachadaBaseDatos;

public class GestionDistribuidor {
    FachadaBaseDatos fbd;

    public GestionDistribuidor( FachadaBaseDatos fbd) {
        this.fbd = fbd;
    }
    
    public ArrayList<Distribuidor> obtenerDistribuidores(){
        return fbd.obtenerDistribuidores();
    }
}
