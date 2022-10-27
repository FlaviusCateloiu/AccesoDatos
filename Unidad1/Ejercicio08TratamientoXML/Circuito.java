package Ejercicio08TratamientoXML;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;

@XmlRootElement (name = "race")
@XmlType(propOrder = {"round", "country", "city", "circuitname", "gpname", "racedate", "firstgp", "numberoflaps",
        "circuitlength", "racedistance", "laprecord", "recordowner", "recordyear", "turns", "drszones"})
public class Circuito {
    private int round;
    private String country;
    private String city;
    private String circuitName;
    private String gpname;
    private LocalDate racedate;
    private int firstgp;
    private int numberoflaps;
    private float circuitlength;
    private float racedistance;
    private String laprecord;
    private String recordowner;
    private int recordyear;
    private int turns;
    private int drszones;

    public Circuito(int round, String country, String city, String circuitName, String gpname, LocalDate racedate, int firstgp, int numberoflaps, float circuitlength, float racedistance, String laprecord, String recordowner, int recordyear, int turns, int drszones) {
        this.round = round;
        this.country = country;
        this.city = city;
        this.circuitName = circuitName;
        this.gpname = gpname;
        this.racedate = racedate;
        this.firstgp = firstgp;
        this.numberoflaps = numberoflaps;
        this.circuitlength = circuitlength;
        this.racedistance = racedistance;
        this.laprecord = laprecord;
        this.recordowner = recordowner;
        this.recordyear = recordyear;
        this.turns = turns;
        this.drszones = drszones;
    }

    public Circuito(int round, String gpname) {
        this.round = round;
        this.gpname = gpname;
    }

    @XmlAttribute(name = "round")
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    @XmlElement(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "circuitname")
    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    @XmlElement(name = "gpname")
    public String getGpname() {
        return gpname;
    }

    public void setGpname(String gpname) {
        this.gpname = gpname;
    }

    @XmlElement(name = "racedate")
    public LocalDate getRacedate() {
        return racedate;
    }

    public void setRacedate(LocalDate racedate) {
        this.racedate = racedate;
    }

    @XmlElement(name = "firstgp")
    public int getFirstgp() {
        return firstgp;
    }

    public void setFirstgp(int firstgp) {
        this.firstgp = firstgp;
    }

    @XmlElement(name = "numberoflaps")
    public int getNumberoflaps() {
        return numberoflaps;
    }

    public void setNumberoflaps(int numberoflaps) {
        this.numberoflaps = numberoflaps;
    }

    @XmlElement(name = "circuitlength")
    public float getCircuitlength() {
        return circuitlength;
    }

    public void setCircuitlength(float circuitlength) {
        this.circuitlength = circuitlength;
    }

    @XmlElement(name = "racedistance")
    public float getRacedistance() {
        return racedistance;
    }

    public void setRacedistance(float racedistance) {
        this.racedistance = racedistance;
    }

    @XmlElement(name = "laprecord")
    public String getLaprecord() {
        return laprecord;
    }

    public void setLaprecord(String laprecord) {
        this.laprecord = laprecord;
    }

    @XmlElement(name = "recordowner")
    public String getRecordowner() {
        return recordowner;
    }

    public void setRecordowner(String recordowner) {
        this.recordowner = recordowner;
    }

    @XmlElement(name = "recordyear")
    public int getRecordyear() {
        return recordyear;
    }

    public void setRecordyear(int recordyear) {
        this.recordyear = recordyear;
    }

    @XmlElement(name = "turns")
    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    @XmlElement(name = "drszones")
    public int getDrszones() {
        return drszones;
    }

    public void setDrszones(int drszones) {
        this.drszones = drszones;
    }
}
