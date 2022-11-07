package Ejercicio09TratamientoJSON;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "calendar")
public class Mundial {
    @XmlElement(name = "race")
    private ArrayList<Circuito> circuitos = new ArrayList<>();

    public Mundial() {
    }

    public ArrayList<Circuito> getCircuitos() {
        return circuitos;
    }

    public void setCircuitos(ArrayList<Circuito> circuitos) {
        this.circuitos = circuitos;
    }
}
