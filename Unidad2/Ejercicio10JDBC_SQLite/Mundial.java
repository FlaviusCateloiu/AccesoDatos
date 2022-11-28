package Ejercicio10JDBC_SQLite;

import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Mundial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int minimoEdadDriver = 0;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        Connection conexion = null;

        PreparedStatement sentencia = null;

        try {
            Class.forName("org.sqlite.JDBC");
            Path rutaBaseDatos = Path.of("Unidad2/Ejercicio10JDBC_SQLite/formula1.db");
            conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaBaseDatos.toString());

            // Apartado 1.
            String sentenciaClasificacion = """
                    SELECT SUM(Results.Points) AS TotalPuntos, Drivers.Name 
                    FROM Results 
                    INNER JOIN Drivers 
                        ON Results.DriverID = Drivers.DriverID
                    GROUP BY Drivers.Name
                    ORDER BY TotalPuntos DESC""";
            sentencia = conexion.prepareStatement(sentenciaClasificacion);
            ResultSet resultadosClasPilotos = sentencia.executeQuery();

            System.out.println("1. Clasificación final ordenada del mundial de pilotos");

            System.out.println("Driver\t\t\tTotal Points");
            System.out.println("-".repeat(30));

            while (resultadosClasPilotos.next()) {
                System.out.println(resultadosClasPilotos.getString("name") + "\t" + resultadosClasPilotos.getInt("totalpuntos"));
            }

            resultadosClasPilotos.close();

            // Apartado 2.
            String sentenciaPilotosMasAnyos = """
            SELECT cast(strftime('%Y.%m%d', 'now') - strftime('%Y.%m%d', DateOfBirth ) as int) AS dob, Name
            FROM Drivers
            WHERE dob >= 30
            GROUP BY Name
            ORDER BY dob DESC""";
            sentencia = conexion.prepareStatement(sentenciaPilotosMasAnyos);
            ResultSet resultadoPilotosMasAnyos = sentencia.executeQuery();

            System.out.println("\n2. Pilotos con 30 años o más (a día de hoy), ordenados de mayor a menor edad.");

            System.out.println("Driver\t\t\tYears Driver");
            System.out.println("-".repeat(30));

            while (resultadoPilotosMasAnyos.next()) {
                System.out.println(resultadoPilotosMasAnyos.getString("name") + "\t" + resultadoPilotosMasAnyos.getInt("dob"));
            }

            resultadoPilotosMasAnyos.close();

            //Apartado 3.
            System.out.println("\n3. Como en la consulta anterior, pero permite que sea el usuario el que especifique el límite de edad\n" +
                    "mínima de los pilotos a mostrar.");
            boolean numeroBien = true;
            do {
                try {
                    minimoEdadDriver = sc.nextInt();
                    numeroBien = true;
                } catch (Exception e) {
                    System.out.println("Error introduce un numero entero.");
                    numeroBien = false;
                }
            } while(!numeroBien);
            String sentenciaPilotosEdadIntroducido= """
            SELECT cast(strftime('%Y.%m%d', 'now') - strftime('%Y.%m%d', DateOfBirth ) as int) AS dob, Name
            FROM Drivers
            WHERE dob >= ?
            GROUP BY Name
            ORDER BY dob DESC""";
            sentencia = conexion.prepareStatement(sentenciaPilotosEdadIntroducido);
            sentencia.setInt(1, minimoEdadDriver);
            ResultSet resultadoPilotosEdadIntroducido = sentencia.executeQuery();

            System.out.println("Driver\t\t\tYears Driver");
            System.out.println("-".repeat(30));

            while (resultadoPilotosEdadIntroducido.next()) {
                System.out.println(resultadoPilotosEdadIntroducido.getString("name") + "\t" + resultadoPilotosEdadIntroducido.getInt("dob"));
            };


            //Crear la Tabla Teams.
            String crearTablaTeams = """
                    CREATE TABLE IF NOT EXISTS Teams (
                        Constructor TEXT NOT NULL,
                        Chassis TEXT NOT NULL,
                        PowerUnit TEXT NOT NULL,
                    PRIMARY KEY ( Constructor ));
                    """;
            String borrarDatosTabla = """
                    DELETE FROM Teams""";
            String introducirDatosTeams = """
                    INSERT INTO Teams VALUES
                    ('Alfa Romeo Racing-Ferrari', 'C38', 'Ferrari 064'),
                    ('Ferrari', 'SF90', 'Ferrari 064'),
                    ('Haas-Ferrari', 'VF-19', 'Ferrari 064'),
                    ('McLaren-Renault', 'MCL34', 'Renault E-Tech 19'),
                    ('Mercedes', 'F1 W10 EQ Power+', 'Mercedes M10 EQ Power+'),
                    ('Racing Point-BWT Mercedes', 'RP19', 'BWT Mercedes'),
                    ('Red Bull Racing-Honda', 'RB15', 'Honda RA619H'),
                    ('Renault', 'R.S.19', 'Renault E-Tech 19'),
                    ('Scuderia Toro Rosso-Honda', 'STR14', 'Honda RA619H'),
                    ('Williams-Mercedes', 'FW42', 'Mercedes M10 EQ Power+')""";
            sentencia = conexion.prepareStatement(crearTablaTeams);
            sentencia.execute();
            sentencia = conexion.prepareStatement(borrarDatosTabla);
            sentencia.execute();
            sentencia = conexion.prepareStatement(introducirDatosTeams);
            sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}