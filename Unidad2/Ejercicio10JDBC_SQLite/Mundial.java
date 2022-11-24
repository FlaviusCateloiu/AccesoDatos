package Ejercicio10JDBC_SQLite;

import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Mundial {
    public static void main(String[] args) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        Connection conexion = null;

        PreparedStatement sentencia = null;

        try {
            Class.forName("org.sqlite.JDBC");
            Path rutaBaseDatos = Path.of("Unidad2/Ejercicio10JDBC_SQLite/formula1.db");
            conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaBaseDatos.toString());

            // Apartado 1.
            String sentenciaClasificacion = "SELECT Results.Points, Drivers.Name FROM Results INNER JOIN Drivers ON Results.DriverID = Drivers.DriverID";
            sentencia = conexion.prepareStatement(sentenciaClasificacion);
            ResultSet resultadosClasPilotos = sentencia.executeQuery();

            System.out.println("1. Clasificación final ordenada del mundial de pilotos");

            System.out.println("Driver\t\t\tTotal Points");
            System.out.println("-".repeat(30));

            Map<String, Double> mapaClasificacionFinal = new HashMap<>();

            while (resultadosClasPilotos.next()) {
                if (mapaClasificacionFinal.containsKey(resultadosClasPilotos.getString("name"))) {
                    mapaClasificacionFinal.put(resultadosClasPilotos.getString("name"), mapaClasificacionFinal.get(resultadosClasPilotos.getString("name")) + resultadosClasPilotos.getDouble("points"));
                } else {
                    mapaClasificacionFinal.put(resultadosClasPilotos.getString("name"), resultadosClasPilotos.getDouble("points"));
                }
            }
            mapaClasificacionFinal.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(r -> System.out.println(r.getKey() + "\t" + r.getValue()));

            resultadosClasPilotos.close();

            // Apartado 2.
            String sentenciaPilotosMasAnyos = "SELECT strftime('%d/%m/%Y', DateOfBirth) AS dob, Name FROM Drivers";
            sentencia = conexion.prepareStatement(sentenciaPilotosMasAnyos);
            ResultSet resultadoPilotosMasAnyos = sentencia.executeQuery();

            System.out.println("\n2. Pilotos con 30 años o más (a día de hoy), ordenados de mayor a menor edad.");

            System.out.println("Driver\t\t\tYears Driver");
            System.out.println("-".repeat(30));

            Map<String, Integer> mapaPilotoMayorTreinta = new HashMap<>();

            while (resultadoPilotosMasAnyos.next()) {
                mapaPilotoMayorTreinta.put(resultadoPilotosMasAnyos.getString("name"), LocalDate.parse(resultadoPilotosMasAnyos.getString("dob"), dateFormat).until(IsoChronology.INSTANCE.dateNow()).getYears());
            }

            mapaPilotoMayorTreinta.entrySet().stream()
                    .filter(p -> p.getValue() >= 30)
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .forEach(r -> System.out.println(r.getKey() + "\t" + r.getValue()));

            conexion.close();

        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}