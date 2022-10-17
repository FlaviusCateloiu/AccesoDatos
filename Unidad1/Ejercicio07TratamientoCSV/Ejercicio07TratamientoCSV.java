package Ejercicio07TratamientoCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio07TratamientoCSV {
    public static void main(String[] args) {
        List<List<String>> raceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_raceResults.csv");
        List<List<String>> sprintRaceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");
        List<CarreraFinal> carreraFinalResult = new ArrayList<>();
        List<SprintCarrera> carreraSprintResult = new ArrayList<>();
        List<TipoCarrera> corredoresConMasPuntos = new ArrayList<>();

        int position;
        CarreraFinal carreraFinal;
        TipoCarrera carrera;
        int posCorredorMasP = 0;
        float maxPoints = 0, sumPoints;

        for (List<String> linea : raceResult) {
            if (linea.get(1).equals("NC")) {
                position = TipoCarrera.NC;
            } else if (linea.get(1).equals("DQ")) {
                position = TipoCarrera.DQ;
            } else {
                position = Integer.parseInt(linea.get(1));
            }
            carreraFinalResult.add(new CarreraFinal(linea.get(0), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8)), linea.get(9).equals("No") ? false : true, linea.get(10)));
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

        for (CarreraFinal carrFinal : carreraFinalResult) {
            for (SprintCarrera sprint : carreraSprintResult) {
                if (carrFinal.getDriver().equalsIgnoreCase(sprint.getDriver())) {
                    carreraFinal = carrFinal;
                    sumPoints = carrFinal.getPoints() + sprint.getPoints();
                    corredoresConMasPuntos.add(carreraFinal);
                }
            }
        }

        for (int i = 0; i < corredoresConMasPuntos.size(); i++) {
            carrera = corredoresConMasPuntos.get(i);
            if (carrera.getPoints() > maxPoints) {
                posCorredorMasP = i;
                maxPoints = carrera.getPoints();
            }
        }

        float finalMaxPoints = maxPoints;
        carreraFinalResult.stream().filter(carr -> carr.getPoints() >= finalMaxPoints).map(carr -> carr.getDriver()).distinct().forEach(System.out::println);
        System.out.println();
        corredoresConMasPuntos.stream().filter(carr -> carr.getPoints() >= finalMaxPoints).map(carr -> carr.getDriver()).distinct().forEach(System.out::println);
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
