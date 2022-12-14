package Ejercicio08TratamientoXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import Ejercicio07TratamientoCSV.Conductor;
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
                boolean ok = true;
                for (int i = 0; i < listaCircuitos.size() && ok; i++) {
                    if (listaCircuitos.get(i).getGpname().equalsIgnoreCase(linea.get(0))) {
                        carreraFinalResult.add(new CarreraFinal(listaCircuitos.get(i), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8)), !linea.get(9).equals("No"), linea.get(10)));
                        ok = false;
                    }
                }
            }

            for (List<String> linea : sprintRaceResult) {
                if (linea.get(1).equals("NC")) {
                    position = TipoCarrera.NC;
                } else if (linea.get(1).equals("DQ")) {
                    position = TipoCarrera.DQ;
                } else {
                    position = Integer.parseInt(linea.get(1));
                }
                boolean ok = true;
                for (int i = 0; i < listaCircuitos.size() && ok; i++) {
                    if (listaCircuitos.get(i).getGpname().equalsIgnoreCase(linea.get(0))) {
                        carreraSprintResult.add(new SprintCarrera(listaCircuitos.get(i), position, Integer.parseInt(linea.get(2)), linea.get(3), linea.get(4), Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8))));                        ok = false;
                    }
                }
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

            System.out.print("-Campeon F1: ");
            List<Conductor> campeon = listaConductores.stream().sorted((c1, c2) -> Double.compare(c2.getTotalPuntos(), c1.getTotalPuntos())).limit(1).toList();
            System.out.println(campeon.get(0).getNombre());
            System.out.println();

            List<CarreraFinal> listadoCadaCircuito = new ArrayList<>();

            boolean ok = true;
            for (Circuito c : listaCircuitos) {
                for (int i = 0; i < carreraFinalResult.size() && ok; i++) {
                    if (carreraFinalResult.get(i).getTrack().getGpname().equalsIgnoreCase(c.getGpname())) {
                        ok = false;
                        listadoCadaCircuito.add(carreraFinalResult.get(i));
                    }
                }
                ok = true;
            }

            listadoCadaCircuito.stream().filter(c -> c.getDriver().equalsIgnoreCase(campeon.get(0).getNombre())).map(c -> c.getTrack().getGpname()).forEach(System.out::println);

            System.out.println();
            System.out.println("-Grandes Premios Celebrados por pais: ");
            HashMap<String, Integer> grandesPremiosPaises = new HashMap<>();

            for (Circuito c : listaCircuitos) {
                if (!grandesPremiosPaises.containsKey(c.getCountry())) {
                    grandesPremiosPaises.put(c.getCountry(), 1);
                } else {
                    grandesPremiosPaises.put(c.getCountry(), grandesPremiosPaises.get(c.getCountry()) + 1);
                }
            }

            grandesPremiosPaises.entrySet().stream().sorted((c1, c2) -> Integer.compare(c2.getValue(), c1.getValue())).forEach(System.out::println);

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
