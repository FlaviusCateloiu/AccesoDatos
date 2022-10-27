package Ejercicio08TratamientoXML;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Ejercicio08TratamientoXML {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad1/Ejercicio08TratamientoXML","formula1_2021season_calendar.xml");
        JAXBContext context = null;


        try {
            context = JAXBContext.newInstance(Circuito.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            Mundial mundial = (Mundial) jaxbUnmarshaller.unmarshal(nombreFichero.toFile());

            ArrayList<Circuito> listaCircuitos = mundial.getListaCircuitos();

            for (Circuito c : listaCircuitos) {
                System.out.println(c.getGpname());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
