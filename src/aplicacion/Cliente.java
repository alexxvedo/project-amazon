package aplicacion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {

    private int id;
    private String nombre;
    private int telefono;
    private Date fechaNacimiento;
    private boolean prime;
    private String email;
    private String contrasena;

    public static String format = "yyyy-MM-dd";

    public Cliente(int id, String nombre, int telefono, Date fechaNacimiento, boolean prime, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.prime = prime;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getTelefonoString() {
        return "" + this.telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaNacimientoString() {
        return new SimpleDateFormat(this.format).format(this.fechaNacimiento);
    }

    public boolean isPrime() {
        return prime;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + '}';
    }

}
