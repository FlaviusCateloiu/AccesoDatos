package Ejercicio10SQLiteYMariaDBconAWS;

import java.nio.file.Path;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Mundial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int minimoEdadDriver = 0;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        Connection conexion = null;

        PreparedStatement sentencia = null;

        System.out.println("Parte SQLite:");
        //Parte 1 SQLite.
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
            ORDER BY dob DESC""";
            sentencia = conexion.prepareStatement(sentenciaPilotosEdadIntroducido);
            sentencia.setInt(1, minimoEdadDriver);
            ResultSet resultadoPilotosEdadIntroducido = sentencia.executeQuery();

            System.out.println("Driver\t\t\tYears Driver");
            System.out.println("-".repeat(30));

            while (resultadoPilotosEdadIntroducido.next()) {
                System.out.println(resultadoPilotosEdadIntroducido.getString("name") + "\t" + resultadoPilotosEdadIntroducido.getInt("dob"));
            };

            resultadoPilotosEdadIntroducido.close();

            //Crear la Tabla Teams.
            String crearTablaTeams = """
                    CREATE TABLE IF NOT EXISTS Teams (
                        Constructor TEXT NOT NULL,
                        Chassis TEXT NOT NULL,
                        PowerUnit TEXT NOT NULL,
                    PRIMARY KEY ( Constructor ))
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
            sentencia.executeUpdate();
            sentencia = conexion.prepareStatement(borrarDatosTabla);
            sentencia.executeUpdate();
            sentencia = conexion.prepareStatement(introducirDatosTeams);
            sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        //Parte 2 MariaDB y AWS.
        System.out.println("\nParte Maria DB:");
        String port = "3306";
        String bbdd = "f1_2019";

        String dbUrl = "jdbc:mariadb://f12019.ci66saah1axn.us-east-1.rds.amazonaws.com:" + port + "/" + bbdd;
        String usuario = "admin";
        String passwd = "aadd1234";

        try (Connection conexionMDB = DriverManager.getConnection(dbUrl, usuario, passwd)) {
            String contenidoMDB1 = """
                    SELECT SUM(Results.Points) AS TotalPuntos, Drivers.Name 
                    FROM Results 
                    INNER JOIN Drivers 
                        ON Results.DriverID = Drivers.DriverID
                    ORDER BY TotalPuntos DESC""";
            PreparedStatement sentenciaMDB = conexionMDB.prepareStatement(contenidoMDB1);
            ResultSet resultadosMDB1 = sentenciaMDB.executeQuery();

            System.out.println("1. Clasificación final ordenada del mundial de pilotos");
            System.out.println("Driver\t\t\tTotal Points");
            System.out.println("-".repeat(30));
            while (resultadosMDB1.next()) {
                System.out.println(resultadosMDB1.getString("name") + "\t" + resultadosMDB1.getInt("totalpuntos"));
            }

            resultadosMDB1.close();

            String contenidoMDB2 = """
            SELECT TIMESTAMPDIFF(YEAR,DateOfBirth,CURDATE()) AS dob, Name
            FROM Drivers
            WHERE TIMESTAMPDIFF(YEAR,DateOfBirth,CURDATE()) >= 30
            ORDER BY TIMESTAMPDIFF(YEAR,DateOfBirth,CURDATE()) DESC""";
            sentenciaMDB = conexionMDB.prepareStatement(contenidoMDB2);
            ResultSet resultadosMDB2 = sentenciaMDB.executeQuery();

            System.out.println("\n2. Pilotos con 30 años o más (a día de hoy), ordenados de mayor a menor edad.");

            System.out.println("Driver\t\t\tYears Driver");
            System.out.println("-".repeat(30));

            while (resultadosMDB2.next()) {
                System.out.println(resultadosMDB2.getString("name") + "\t" + resultadosMDB2.getInt("dob"));
            }

            resultadosMDB2.close();
            sentenciaMDB.close();

        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}