/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.util.ArrayList;
import baseDatos.FachadaBaseDatos;

/**
 *
 * @author alumnogreibd
 */
public class GestionDistribuidor {
    FachadaBaseDatos fbd;

    public GestionDistribuidor( FachadaBaseDatos fbd) {
        this.fbd = fbd;
    }
    
    public ArrayList<Distribuidor> obtenerDistribuidores(){
        return fbd.obtenerDistribuidores();
    }
}
