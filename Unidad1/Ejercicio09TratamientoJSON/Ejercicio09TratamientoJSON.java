package Ejercicio09TratamientoJSON;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Ejercicio09TratamientoJSON {
    public static void main(String[] args) {
        List<List<String>> raceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_raceResults.csv");
        List<List<String>> sprintRaceResult = lecturaCSV("Unidad1/Ejercicio07TratamientoCSV/formula1_2021season_sprintQualifyingResults.csv");
        List<CarreraFinal> carreraFinalResult = new ArrayList<>();
        List<SprintCarrera> carreraSprintResult = new ArrayList<>();
        List<TipoCarrera> todasLasCarreras = new ArrayList<>();
        int position = 0;
        boolean ok;
        Path nombreFichero = Path.of("Unidad1/Ejercicio08TratamientoXML","formula1_2021season_calendar.xml");
        JAXBContext context = null;
        Circuito circuito = null;
        Teams equipo = null;
        Drivers piloto = null;

        List<Teams> listaEquipos = new ArrayList<>();
        List<Drivers> listaPilotos = new ArrayList<>();

        listaEquipos = leerJSONTeams();
        listaPilotos = leerJSONDrivers();


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
                ok = true;
                for (int i = 0; i < listaPilotos.size() && ok; i++) {
                    if (listaPilotos.get(i).getDriver().equalsIgnoreCase(linea.get(3))) {
                        piloto = listaPilotos.get(i);
                        ok = false;
                    }
                }
                ok = true;
                for (int i = 0; i < listaEquipos.size() && ok; i++) {
                    if (listaEquipos.get(i).getTeam().equalsIgnoreCase(linea.get(4))) {
                        equipo = listaEquipos.get(i);
                        ok = false;
                    }
                }
                ok = true;
                for (int i = 0; i < listaCircuitos.size() && ok; i++) {
                    if (listaCircuitos.get(i).getGpname().equalsIgnoreCase(linea.get(0))) {
                        carreraFinalResult.add(new CarreraFinal(listaCircuitos.get(i), position, Integer.parseInt(linea.get(2)), piloto, equipo, Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8)), !linea.get(9).equals("No"), linea.get(10)));
                        ok = false;
                    }
                }
                ok = true;
            }

            for (List<String> linea : sprintRaceResult) {
                if (linea.get(1).equals("NC")) {
                    position = TipoCarrera.NC;
                } else if (linea.get(1).equals("DQ")) {
                    position = TipoCarrera.DQ;
                } else {
                    position = Integer.parseInt(linea.get(1));
                }
                ok = true;
                for (int i = 0; i < listaPilotos.size() && ok; i++) {
                    if (listaPilotos.get(i).getDriver().equalsIgnoreCase(linea.get(3))) {
                        piloto = listaPilotos.get(i);
                        ok = false;
                    }
                }
                ok = true;
                for (int i = 0; i < listaEquipos.size() && ok; i++) {
                    if (listaEquipos.get(i).getTeam().equalsIgnoreCase(linea.get(4))) {
                        equipo = listaEquipos.get(i);
                        ok = false;
                    }
                }
                ok = true;
                for (int i = 0; i < listaCircuitos.size() && ok; i++) {
                    if (listaCircuitos.get(i).getGpname().equalsIgnoreCase(linea.get(0))) {
                        carreraSprintResult.add(new SprintCarrera(listaCircuitos.get(i), position, Integer.parseInt(linea.get(2)), piloto, equipo, Integer.parseInt(linea.get(5)), Integer.parseInt(linea.get(6)), linea.get(7), Float.parseFloat(linea.get(8))));                        ok = false;
                        ok = false;
                    }
                }
                ok = true;
            }

            todasLasCarreras.addAll(carreraFinalResult);
            todasLasCarreras.addAll(carreraSprintResult);

            System.out.println("---Pilotos que han sido o son campeones del mundo.---");
            listaPilotos.stream().filter(p -> p.getWorldChampionships() > 0).map(Drivers::getDriver).forEach(System.out::println);
            System.out.println();

            System.out.println("---Escuderia con Pilotos de su pais.---");
            /*carreraFinalResult.stream()
                    .filter(p -> p.getTeam().getBaseCountry().equalsIgnoreCase(p.getDriver().getCountry()) )
                    .map(c -> c.getTeam().getTeam())
                    .distinct().forEach(System.out::println);*/
            List<String> listaEscuPilotosPais = new ArrayList<>();
            Map<String, String> hashMapEquiposCiudad = new HashMap<>();
            for (Teams t : listaEquipos) {
                hashMapEquiposCiudad.put(t.getTeam(), t.getBaseCountry());
            }
            for (Map.Entry<String, String> e : hashMapEquiposCiudad.entrySet()) {
                ok = true;
                for (int i = 0; i < listaPilotos.size() && ok; i++) {
                    if (listaPilotos.get(i).getTeam().equalsIgnoreCase(e.getKey())) {
                        if (listaPilotos.get(i).getCountry().equalsIgnoreCase(e.getValue())) {
                            listaEscuPilotosPais.add(e.getKey());
                            ok = false;
                        }
                    }
                }
            }
            listaEscuPilotosPais.stream().distinct().forEach(System.out::println);
            System.out.println();

            System.out.println("---Suministros de motores en la competición---");
            List<String> listaSuministrosEscu = listaEquipos.stream().map(Teams::getPowerUnit).distinct().toList();
            for (String sM : listaSuministrosEscu) {
                System.out.print(sM + ": ");
                listaEquipos.stream().filter(p -> p.getPowerUnit().equalsIgnoreCase(sM)).forEach(p -> System.out.print(p.getTeam() + ", "));
                System.out.println();
            }
            System.out.println();



        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public static List<Teams> leerJSONTeams() {
        List<Teams> lista = new ArrayList<>();
        try {
            String data = new String(Files.readString(Paths.get("Unidad1/Ejercicio09TratamientoJSON/formula1_2021season_teams.json")));
            Gson gson = new Gson();
            lista = Arrays.stream(gson.fromJson(data, Teams[].class)).toList();
        } catch (IOException e) {
            System.err.println("No se ha podido encontrar el archivo JSON de Teams.");
        }
        return lista;
    }

    public static List<Drivers> leerJSONDrivers() {
        List<Drivers> lista = new ArrayList<>();
        try {
            String data = new String(Files.readString(Paths.get("Unidad1/Ejercicio09TratamientoJSON/formula1_2021season_drivers.json")));
            Gson gson = new Gson();
            lista = Arrays.stream(gson.fromJson(data, Drivers[].class)).toList();
        } catch (IOException e) {
            System.err.println("No se ha podido encontrar el archivo JSON de Drivers.");
        }

        return lista;
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
