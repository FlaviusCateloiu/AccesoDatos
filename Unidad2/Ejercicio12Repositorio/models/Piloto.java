package Ejercicio12Repositorio.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Piloto {
    private String nombre;
    private int numero;
    private int numeroCoche;
    private Escuderia equipo;
    private LocalDate fechaNacimiento;

    public Piloto(String nombre, int numero, int numeroCoche, Escuderia equipo, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.numero = numero;
        this.numeroCoche = numeroCoche;
        this.equipo = equipo;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    public int getNumero() { return numero;}
    public int getNumeroCoche() { return numeroCoche;}
    public Escuderia getEquipo() { return equipo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public String getFechaNacimientoString() {
        return fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setNumeroCoche(int numeroCoche) { this.numeroCoche = numeroCoche; }
    public void setEquipo(Escuderia equipo) { this.equipo = equipo; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return "Piloto{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", numeroCoche=" + numeroCoche +
                ", equipo='" + equipo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                '}';
    }
}
