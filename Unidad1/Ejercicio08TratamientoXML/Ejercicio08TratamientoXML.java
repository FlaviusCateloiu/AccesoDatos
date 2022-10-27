package Ejercicio08TratamientoXML;

import java.nio.file.Path;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Ejercicio08TratamientoXML {
    public static void main(String[] args) {
        Path nombreFichero = Path.of("Unidad1/Ejercicio08TratamientoXML","formula1_2021season_calendar.xml");
        JAXBContext context = null;

        try {
            context = JAXBContext.newInstance();
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
