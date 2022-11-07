package Ejercicio09TratamientoJSON;

public abstract class TipoCarrera {
    public static final int DQ = 0;
    public static final int NC = -1;
    private Circuito track;
    private int position;
    private int no;
    private Drivers driver;
    private Teams team;
    private int startingGrid;
    private int laps;
    private String time;
    private double points;

    public TipoCarrera(Circuito track, int position, int no, Drivers driver, Teams team, int startingGrid, int laps, String time, double points) {
        this.track = track;
        this.position = position;
        this.no = no;
        this.driver = driver;
        this.team = team;
        this.startingGrid = startingGrid;
        this.laps = laps;
        this.time = time;
        this.points = points;
    }

    public Circuito getTrack() {
        return track;
    }

    public void setTrack(Circuito track) {
        this.track = track;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public int getStartingGrid() {
        return startingGrid;
    }

    public void setStartingGrid(int startingGrid) {
        this.startingGrid = startingGrid;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return track + ", " + position + ", " + driver + ", " + team + ", " + points;
    }
}
