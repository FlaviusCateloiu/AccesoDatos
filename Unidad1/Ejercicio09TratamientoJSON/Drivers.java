package Ejercicio09TratamientoJSON;

public class Drivers {
    private String driver;
    private String abbreviation;
    private int number;
    private String team;
    private String country;
    private int worldChampionships;
    private String dateBrith;
    private String placeBirth;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(int worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    public String getDateBrith() {
        return dateBrith;
    }

    public void setDateBrith(String dateBrith) {
        this.dateBrith = dateBrith;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "driver='" + driver + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", number=" + number +
                ", team='" + team + '\'' +
                ", country='" + country + '\'' +
                ", worldChampionships=" + worldChampionships +
                ", dateBrith='" + dateBrith + '\'' +
                ", placeBirth='" + placeBirth + '\'' +
                '}';
    }
}
