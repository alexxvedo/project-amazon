/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.EmpresaVendedora;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class DAOEmpresasVendedoras extends AbstractDAO{
    
    
    public DAOEmpresasVendedoras(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<EmpresaVendedora> obtenerEmpresasVendedoras() {

        ArrayList<EmpresaVendedora> empresas = new ArrayList<>();
        Connection con;
        PreparedStatement stmEmpresasVendedora = null;
        ResultSet rsEmpresaVendedora;

        con = this.getConexion();
        

        try {

            stmEmpresasVendedora = con.prepareStatement("select * from empresasVendedoras");
            
            rsEmpresaVendedora = stmEmpresasVendedora.executeQuery();

            while (rsEmpresaVendedora.next()) {

                EmpresaVendedora empresa = new EmpresaVendedora(rsEmpresaVendedora.getInt("id"),rsEmpresaVendedora.getString("nombre"), rsEmpresaVendedora.getDate("fechaAsociacion"));
                empresas.add(empresa);
                System.out.println(empresa.toString());

            }
            

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmEmpresasVendedora.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return empresas;

    }
    
}
