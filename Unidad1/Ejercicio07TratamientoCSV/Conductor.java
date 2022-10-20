package Ejercicio07TratamientoCSV;

public class Conductor {
    private String nombre;
    private double totalPuntos;

    public Conductor(String nombre, double totalPuntos) {
        this.nombre = nombre;
        this.totalPuntos = totalPuntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(double totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n"
                + "Total: " + totalPuntos;
    }
}
