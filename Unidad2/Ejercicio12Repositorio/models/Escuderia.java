package Ejercicio12Repositorio.models;

public class Escuderia {
    private int numero;
    private String nombre;
    private String chasis;
    private String motor;

    public Escuderia(int numero, String nombre, String chasis, String motor) {
        this.numero = numero;
        this.nombre = nombre;
        this.chasis = chasis;
        this.motor = motor;
    }

    public Escuderia(String nombre, String chasis, String motor) {
        this.nombre = nombre;
        this.chasis = chasis;
        this.motor = motor;
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

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Escuderia{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", chasis='" + chasis + '\'' +
                ", motor='" + motor + '\'' +
                '}';
    }
}