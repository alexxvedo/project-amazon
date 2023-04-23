package baseDatos;

import aplicacion.Cliente;
import aplicacion.Direccion;
import aplicacion.Distribuidor;
import aplicacion.MetodoPago;
import aplicacion.Pedido;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPedido extends AbstractDAO {

    public DAOPedido(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Pedido> obtenerPedidos(Cliente c) {

        ArrayList<Pedido> pedidos = new ArrayList<>();
        Connection con;
        PreparedStatement stmPedidos = null;
        ResultSet rsPedidos;

        con = this.getConexion();

        try {

            stmPedidos = con.prepareStatement("select * from pedidos where idCliente = ?");
            stmPedidos.setInt(1, c.getId());
            rsPedidos = stmPedidos.executeQuery();

            while (rsPedidos.next()) {

                PreparedStatement stmTarjeta = con.prepareStatement("select * from metodosPago where numeroTarjeta = ?");
                stmTarjeta.setLong(1, rsPedidos.getLong("numTarjeta"));
                ResultSet rsTarjeta = stmTarjeta.executeQuery();

                MetodoPago tarjeta = null;

                if (rsTarjeta.next()) {
                    tarjeta = new MetodoPago(rsTarjeta.getLong("numeroTarjeta"), c, rsTarjeta.getBoolean("activa"), rsTarjeta.getBoolean("preferida"));
                }

                PreparedStatement stmDireccion = con.prepareStatement("select * from direcciones where id = ?");
                stmDireccion.setInt(1, rsPedidos.getInt("idDireccion"));
                ResultSet rsDireccion = stmDireccion.executeQuery();

                Direccion direccion = null;

                if (rsDireccion.next()) {
                    direccion = new Direccion(rsDireccion.getInt("id"), rsDireccion.getString("calle"), rsDireccion.getInt("numero"), rsDireccion.getString("ciudad"), rsDireccion.getInt("codigopostal"), rsDireccion.getBoolean("preferida"));
                }

                PreparedStatement stmDistribuidor = con.prepareStatement("select * from distribuidores where id = ?");
                stmDistribuidor.setInt(1, rsPedidos.getInt("idDistribuidor"));
                ResultSet rsDistribuidor = stmDistribuidor.executeQuery();

                Distribuidor distribuidor = null;

                if (rsDistribuidor.next()) {
                    distribuidor = new Distribuidor(rsDistribuidor.getInt("id"), rsDistribuidor.getString("nombre"), rsDistribuidor.getInt("telefono"), rsDistribuidor.getFloat("costeEnvio"));
                }

                Pedido pedido = new Pedido(rsPedidos.getInt("id"), rsPedidos.getDate("fechaPedido"), rsPedidos.getDate("fechaSalida"), rsPedidos.getDate("fechaLlegada"), rsPedidos.getBoolean("completado"), rsPedidos.getFloat("precioTotal"), tarjeta, direccion, c, distribuidor);
                pedidos.add(pedido);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage(), Color.RED);

        } finally {

            try {

                stmPedidos.close();

            } catch (SQLException e) {

                System.out.println("Imposible cerrar cursores");

            }

        }

        return pedidos;

    }

}
