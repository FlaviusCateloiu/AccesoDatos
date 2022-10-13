package Ejercicio07TramientoCSV;

public class CarreraFinal extends TipoCarrera {
    private boolean extraPoint;
    private String fastestLap;

    public CarreraFinal(String track, int position, int no, String driver, String team, int startingGrid, int laps, String time, int points, boolean extraPoint, String fastestLap) {
        super(track, position, no, driver, team, startingGrid, laps, time, points);
        this.extraPoint = extraPoint;
        this.fastestLap = fastestLap;
    }

    public boolean getExtraPoint() {
        return extraPoint;
    }

    public void setExtraPoint(boolean extraPoint) {
        this.extraPoint = extraPoint;
    }

    public String getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(String fastestLap) {
        this.fastestLap = fastestLap;
    }
}
