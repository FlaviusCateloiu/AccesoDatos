package Ejercicio12Repositorio.models;

public class Resultados {
    private int numero;
    private Piloto piloto;
    private Circuito circuito;
    private int posicion;
    private int puntos;

    public Resultados(int numero, Piloto piloto, Circuito circuito, int posicion, int puntos) {
        this.numero = numero;
        this.piloto = piloto;
        this.circuito = circuito;
        this.posicion = posicion;
        this.puntos = puntos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Resultados{" +
                "numero=" + numero +
                ", piloto=" + piloto +
                ", circuito=" + circuito +
                ", posicion=" + posicion +
                ", puntos=" + puntos +
                '}';
    }
}
