package Ejercicio07TratamientoCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Ejercicio07TratamientoCSV {

    /* https://stackoverflow.com/questions/34997501/java-8-stream-groupingby-summing-an-attributevalue */
    public static void main(String[] args) {
        List<List<String>> raceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_raceResults.csv");
        List<List<String>> sprintRaceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");
        List<CarreraFinal> carreraFinalResult = new ArrayList<>();
        List<SprintCarrera> carreraSprintResult = new ArrayList<>();
        List<TipoCarrera> todasLasCarreras = new ArrayList<>();

        int position = 0;


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

        todasLasCarreras.addAll(carreraFinalResult);
        todasLasCarreras.addAll(carreraSprintResult);

        Map<List<String>, Double> mapConductor = todasLasCarreras.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getDriver()),
                        Collectors.summingDouble(TipoCarrera::getPoints)
                ));

        List<Conductor> listaConductores = mapConductor.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey().get(0), c.getValue())).toList();

        System.out.print("Campeon F1: ");
        listaConductores.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).forEach(System.out::println);
        System.out.println();

        Map<List<String>, Double> mapEscuderia = todasLasCarreras.stream()
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getTeam()),
                        Collectors.summingDouble(TipoCarrera::getPoints)
                ));

        List<Conductor> listaEscuderias = mapEscuderia.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey().get(0), c.getValue())).toList();

        System.out.print("Escuderia Campeona del año pasado: ");
        listaEscuderias.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).forEach(System.out::println);
        System.out.println();

        Map<List<String>, Double> mapCondVic = carreraFinalResult.stream()
                .filter(p -> p.getPosition() == 1)
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getDriver()),
                        Collectors.summingDouble(TipoCarrera::getPosition)
                ));

        List<Conductor> listaCondVic = mapCondVic.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey().get(0), c.getValue())).toList();

        System.out.println("Corredor con mas victorias: ");
        listaCondVic.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).forEach(System.out::println);
        System.out.println();

        Map<List<String>, Double> mapEscudVic = carreraFinalResult.stream()
                .filter(p -> p.getPosition() == 1)
                .collect(Collectors.groupingBy(
                        p -> Arrays.asList(p.getTeam()),
                        Collectors.summingDouble(TipoCarrera::getPosition)
                ));

        List<Conductor> listaEscudVic = mapEscudVic.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey().get(0), c.getValue())).toList();

        System.out.println("Escuderia con mas victorias: ");
        listaEscudVic.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).forEach(System.out::println);
        System.out.println();

        Map<String, Long> mapCondMasPod = carreraFinalResult.stream()
                .filter(p -> p.getPosition() < 4 && p.getPosition() > 0)
                .collect(Collectors.groupingBy(
                        TipoCarrera::getDriver,
                        Collectors.counting()
                ));

        List<Conductor> listaCondMasPod = mapCondMasPod.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey(), c.getValue())).toList();

        System.out.println("Corredor con mas veces podio: ");
        listaCondMasPod.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).forEach(System.out::println);
        System.out.println();

        Map<String, Long> mapEscudMasPod = carreraFinalResult.stream()
                .filter(p -> p.getPosition() < 4 && p.getPosition() > 0)
                .collect(Collectors.groupingBy(
                        TipoCarrera::getTeam,
                        Collectors.counting()
                ));

        List<Conductor> listaEscudMasPod = mapEscudMasPod.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey(), c.getValue())).toList();

        System.out.println("Escuderia con mas veces podio: ");
        listaEscudMasPod.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).forEach(System.out::println);
        System.out.println();

        Map<String, Long> mapCorrMasPole = carreraFinalResult.stream()
                .filter(p -> p.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(
                        TipoCarrera::getDriver,
                        Collectors.counting()
                ));

        System.out.println("Corredor con mas poles: ");
        mapCorrMasPole.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey(), c.getValue()))
                .sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1)
                .forEach(System.out::println);
        System.out.println();

        Map<String, Long> mapEscudMasPole = carreraFinalResult.stream()
                .filter(p -> p.getStartingGrid() == 1)
                .collect(Collectors.groupingBy(
                        TipoCarrera::getTeam,
                        Collectors.counting()
                ));

        System.out.println("Escuderia con mas poles: ");
        mapEscudMasPole.entrySet()
                .stream()
                .map(c -> new Conductor(c.getKey(), c.getValue()))
                .sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Corredor con mas vueltas rapidas: ");

        System.out.println();
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
    private class vultasRapidasCorredor {
        private String nomCorr;
        private double numVultRap;

        private vultasRapidasCorredor(String nomCorr, double numVultRap) {
            this.nomCorr = nomCorr;
            this.numVultRap = numVultRap;
        }

        @Override
        public String toString() {
            return "vultasRapidasCorredor{" +
                    "nomCorr='" + nomCorr + '\''  +
                    ", numVultRap=" + numVultRap +
                    '}';
        }
    }
}


