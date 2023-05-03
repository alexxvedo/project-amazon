/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Almacen;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class DAOAlmacen extends AbstractDAO{
    
    
    public DAOAlmacen(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<Almacen> obtenerAlmacenes() {

        ArrayList<Almacen> almacenes = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlmacenes = null;
        ResultSet rsAlmacenes;

        con = this.getConexion();

        try {

            stmAlmacenes = con.prepareStatement("select * from almacenes");
            
            rsAlmacenes = stmAlmacenes.executeQuery();

            while (rsAlmacenes.next()) {

                Almacen almacen = new Almacen(rsAlmacenes.getInt("id"),rsAlmacenes.getString("calle"), rsAlmacenes.getInt("numero"), rsAlmacenes.getString("ciudad"), rsAlmacenes.getInt("codigoPostal"));
                almacenes.add(almacen);

            }
            

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmAlmacenes.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return almacenes;

    }
    
}
