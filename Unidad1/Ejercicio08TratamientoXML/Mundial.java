package Ejercicio08TratamientoXML;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;

@XmlRootElement(name = "calendar")
@XmlType(propOrder = {"race"})
public class Mundial {
    private ArrayList<Circuito> listaCircuitos = new ArrayList<>();

    public Mundial() {
    }

    public ArrayList<Circuito> getListaCircuitos() {
        return listaCircuitos;
    }

    public void setListaCircuitos(ArrayList<Circuito> listaCircuitos) {
        this.listaCircuitos = listaCircuitos;
    }
}
