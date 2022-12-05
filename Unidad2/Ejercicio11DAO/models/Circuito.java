package Ejercicio11DAO.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Circuito {
    private int numero;
    private String nombre;
    private LocalDate fechaCircuito;

    public Circuito(int numero, String nombre, LocalDate fechaCircuito) {
        this.numero = numero;
        this.nombre  = nombre;
        this.fechaCircuito = fechaCircuito;
    }

    public String getFechaCircuitoString() {
        return fechaCircuito.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCircuito() {
        return fechaCircuito;
    }

    public void setFechaCircuito(LocalDate fechaCircuito) {
        this.fechaCircuito = fechaCircuito;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", fechaCircuito=" + fechaCircuito.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                '}';
    }
}
