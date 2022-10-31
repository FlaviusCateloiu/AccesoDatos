package Ejercicio08TratamientoXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Ejercicio07TratamientoCSV.CarreraFinal;
import Ejercicio07TratamientoCSV.SprintCarrera;
import Ejercicio07TratamientoCSV.TipoCarrera;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Ejercicio08TratamientoXML {
    public static void main(String[] args) {
        List<List<String>> raceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_raceResults.csv");
        List<List<String>> sprintRaceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");
        List<CarreraFinal> carreraFinalResult = new ArrayList<>();
        List<SprintCarrera> carreraSprintResult = new ArrayList<>();
        List<TipoCarrera> todasLasCarreras = new ArrayList<>();
        int position = 0;
        Path nombreFichero = Path.of("Unidad1/Ejercicio08TratamientoXML","formula1_2021season_calendar.xml");
        JAXBContext context = null;

        try {
            context = JAXBContext.newInstance(Mundial.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            Mundial mundial = (Mundial) jaxbUnmarshaller.unmarshal(nombreFichero.toFile());

            ArrayList<Circuito> listaCircuitos = mundial.getCircuitos();


            for (List<String> linea : raceResult) {
                if (linea.get(1).equals("NC")) {
                    position = TipoCarrera.NC;
                } else if (linea.get(1).equals("DQ")) {
                    position = TipoCarrera.DQ;
                } else {
                    position = Integer.parseInt(linea.get(1));
                }
                carreraFinalResult.add(new CarreraFinal(linea.get(0), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8)), !linea.get(9).equals("No"), linea.get(10)));
            }

            for (List<String> linea : sprintRaceResult) {
                if (linea.get(1).equals("NC")) {
                    position = TipoCarrera.NC;
                } else if (linea.get(1).equals("DQ")) {
                    position = TipoCarrera.DQ;
                } else {
                    position = Integer.parseInt(linea.get(1));
                }
                carreraSprintResult.add(new SprintCarrera(linea.get(0), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8))));
            }

            todasLasCarreras.addAll(carreraFinalResult);
            todasLasCarreras.addAll(carreraSprintResult);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static List<List<String>> lecturaCSV(String ruta) {
        List<List<String>> race = new ArrayList<>();
        try {
            race = Files.lines(Paths.get(ruta)).map(linea -> Arrays.asList(linea.split(","))).skip(1).toList();
        } catch (IOException e) {
            System.err.println("Error no se puede encuantrar el archivo");
        }

        return race;
    }
}
