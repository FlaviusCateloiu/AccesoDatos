import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {

    private static final long serialVersionUID = 999L;
    private String nombre;
    private String email;
    private Date anyoNacimiento;

    public Persona(String nombre, String email, Date anyoNacimiento) {
        this.nombre = nombre;
        this.email = email;
        this.anyoNacimiento = anyoNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(Date anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + ", Email: " + getEmail() + " y Fecha de Nacimiento: " + getAnyoNacimiento();
    }
}
