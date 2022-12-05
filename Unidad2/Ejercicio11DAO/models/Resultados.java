package Ejercicio11DAO.models;

public class Resultados {
    private Piloto piloto;
    private Circuito  circuito;
    private int posicion;
    private int puntos;

    public Resultados(Piloto piloto, Circuito circuito, int posicion, int puntos) {
        this.piloto = piloto;
        this.circuito = circuito;
        this.posicion = posicion;
        this.puntos = puntos;
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
        return "Results{" +
                "piloto=" + piloto +
                ", circuito=" + circuito +
                ", posicion=" + posicion +
                ", puntos=" + puntos +
                '}';
    }
}
