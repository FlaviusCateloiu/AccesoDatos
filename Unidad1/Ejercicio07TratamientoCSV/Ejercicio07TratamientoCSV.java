package Ejercicio07TratamientoCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Ejercicio07TratamientoCSV {

    /* https://stackoverflow.com/questions/34997501/java-8-stream-groupingby-summing-an-attributevalue */
    public static void main(String[] args) {
        List<List<String>> raceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_raceResults.csv");
        List<List<String>> sprintRaceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");
        List<CarreraFinal> carreraFinalResult = new ArrayList<>();
        List<SprintCarrera> carreraSprintResult = new ArrayList<>();
        List<TipoCarrera> todasLasCarreras = new ArrayList<>();
        HashMap<String, Float> corredoresPutos = new HashMap<>();

        int position = 0;
        float puntosCorredor, maxValor = 0;


        for (List<String> linea : raceResult) {
            if (linea.get(1).equals("NC")) {
                position = TipoCarrera.NC;
            } else if (linea.get(1).equals("DQ")) {
                position = TipoCarrera.DQ;
            } else {
                position = Integer.parseInt(linea.get(1));
            }
            todasLasCarreras.add(new CarreraFinal(linea.get(0), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8)), linea.get(9).equals("No") ? false : true, linea.get(10)));
        }

        for (List<String> linea : sprintRaceResult) {
            if (linea.get(1).equals("NC")) {
                position = TipoCarrera.NC;
            } else if (linea.get(1).equals("DQ")) {
                position = TipoCarrera.DQ;
            } else {
                position = Integer.parseInt(linea.get(1));
            }
            todasLasCarreras.add(new SprintCarrera(linea.get(0), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8))));
        }

        for (int i = 0; i < todasLasCarreras.size(); i++) {
            puntosCorredor = 0;
            for (TipoCarrera car : todasLasCarreras) {
                if (todasLasCarreras.get(i).getDriver().equalsIgnoreCase(car.getDriver())) {
                    puntosCorredor += car.getPoints();
                }
            }
            corredoresPutos.put(todasLasCarreras.get(i).getDriver(), puntosCorredor);
        }

        List<Float> values = corredoresPutos.values().stream().toList();
        List<String> keys = corredoresPutos.keySet().stream().toList();

        for (int i = 0; i < keys.size(); i++) {
            if (values.get(i) >= maxValor) {
                maxValor = values.get(i);
                position = i;
            }
        }

        System.out.println(keys.get(position) + " " + values.get(position));

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
