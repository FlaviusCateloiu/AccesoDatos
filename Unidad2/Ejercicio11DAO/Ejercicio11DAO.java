package Ejercicio11DAO;

import Ejercicio11DAO.dao.sql.SQLiteDAOManagerImpl;
import Ejercicio11DAO.models.Circuito;
import Ejercicio11DAO.models.Escuderia;
import Ejercicio11DAO.models.Piloto;
import Ejercicio11DAO.models.Resultados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Ejercicio11DAO {
    public static void main(String[] args) {

        SQLiteDAOManagerImpl mundial = new SQLiteDAOManagerImpl();

        System.out.println("\nMostrar todas Escuderias");
        System.out.println("-------------------------------");
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo una");
        System.out.println("-------------------------------");
        System.out.println(mundial.getPilotoDAO().findById(3));

        System.out.println("\nInsertar una escudería");
        System.out.println("-------------------------------");
        Escuderia seat = new Escuderia("Seat", "Seat-2019", "Seat 4 Latas");
        mundial.getEscuderiaDAO().save(seat);
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar una escudería");
        System.out.println("-------------------------------");
        seat.setChasis("Seat-2023");
        seat.setMotor("Seat TDI 2.0");
        mundial.getEscuderiaDAO().update(seat);
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar una escudería");
        System.out.println("-------------------------------");
        mundial.getEscuderiaDAO().deleteById("Seat");
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);



        System.out.println("\nMostrar todos Pilotos");
        System.out.println("-------------------------------");
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo un Piloto");
        System.out.println("-------------------------------");
        System.out.println(mundial.getPilotoDAO().findById(3));

        System.out.println("\nInsertar un Piloto");
        System.out.println("-------------------------------");
        Piloto pil = new Piloto("Marcos Lorenzo", 21, mundial.getEscuderiaDAO().findById("Mercedes"), LocalDate.parse("1998-11-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mundial.getPilotoDAO().save(pil);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un Piloto");
        System.out.println("-------------------------------");
        pil.setNombre("Federico Garcia");
        pil.setEquipo(mundial.getEscuderiaDAO().findById("Ferrari"));
        pil.setFechaNacimiento(LocalDate.now());
        mundial.getPilotoDAO().update(pil);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un Piloto");
        System.out.println("-------------------------------");
        mundial.getPilotoDAO().deleteById(21);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);



        System.out.println("\nMostrar todos Circuitos");
        System.out.println("-------------------------------");
        mundial.getCircuitoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo un Circuito");
        System.out.println("-------------------------------");
        System.out.println(mundial.getCircuitoDAO().findById(19));

        System.out.println("\nInsertar un Circuito");
        System.out.println("-------------------------------");
        Circuito circ = new Circuito(99, "Rumania", LocalDate.parse("2019-12-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mundial.getCircuitoDAO().save(circ);
        mundial.getCircuitoDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un Circuito");
        System.out.println("-------------------------------");
        circ.setNombre("Moldova");
        circ.setFechaCircuito(LocalDate.now());
        mundial.getCircuitoDAO().update(circ);
        mundial.getCircuitoDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un Circuito");
        System.out.println("-------------------------------");
        mundial.getCircuitoDAO().deleteById(99);
        mundial.getCircuitoDAO().findAll().forEach(System.out::println);



        System.out.println("\nMostrar todos Resultados");
        System.out.println("-------------------------------");
        mundial.getResultadosDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo un Resultado");
        System.out.println("-------------------------------");
        System.out.println(mundial.getResultadosDAO().findById(77, 3));

        System.out.println("\nInsertar un Resultado");
        System.out.println("-------------------------------");
        Resultados resultado = new Resultados( mundial.getPilotoDAO().findById(4), mundial.getCircuitoDAO().findById(21), 20, 2);
        mundial.getResultadosDAO().save(resultado);
        mundial.getResultadosDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un Resultado");
        System.out.println("-------------------------------");
        resultado.setPosicion(30);
        resultado.setPuntos(6);
        mundial.getResultadosDAO().update(resultado);
        mundial.getResultadosDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar un Resultado");
        System.out.println("-------------------------------");
        mundial.getResultadosDAO().deleteById(4, 21);
        mundial.getResultadosDAO().findAll().forEach(System.out::println);

        mundial.close();
    }
}
