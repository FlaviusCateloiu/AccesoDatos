package Ejercicio07TramientoCSV;

public abstract class TipoCarrera {
    private final int dq = 0;
    private final int nc = -1;
    private String track;
    private int position;
    private int no;
    private String driver;
    private String team;
    private int startingGrid;
    private int laps;
    private String time;
    private int points;

    public TipoCarrera(String track, int position, int no, String driver, String team, int startingGrid, int laps, String time, int points) {
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

    public int getDq() {
        return dq;
    }

    public int getNc() {
        return nc;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
