package Ejercicio11DAO;

import Ejercicio11DAO.dao.sql.SQLiteDAOManagerImpl;
import Ejercicio11DAO.models.Escuderia;
import Ejercicio11DAO.models.Piloto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ejercicio11DAO {
    public static void main(String[] args) {

        SQLiteDAOManagerImpl mundial = new SQLiteDAOManagerImpl();

        System.out.println("\nMostrar todas Escuderias");
        System.out.println("-------------------------------");
        mundial.getEscuderiaDAO().findAll();//.forEach(System.out::println);

        /*System.out.println("\nMostrar solo una");
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
        mundial.getEscuderiaDAO().findAll().forEach(System.out::println);*/


        System.out.println("\nMostrar todos Pilotos");
        System.out.println("-------------------------------");
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nMostrar solo un Piloto");
        System.out.println("-------------------------------");
        System.out.println(mundial.getPilotoDAO().findById(3));

        System.out.println("\nInsertar un Piloto");
        System.out.println("-------------------------------");
        Piloto pil = new Piloto("Marcos Lorenzo", 21, new Escuderia("Seat", "Seat-2019", "Seat 4 Latas"), LocalDate.parse("1998-11-23 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(pil);
        mundial.getPilotoDAO().save(pil);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nActualizar un Piloto");
        System.out.println("-------------------------------");
        pil.setNombre("Federico Garcia");
        pil.setEquipo(new Escuderia("Paco", "Paco-2930", "Paco 3423234"));
        pil.setFechaNacimiento(LocalDate.now());
        mundial.getPilotoDAO().update(pil);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        System.out.println("\nBorrar una escudería");
        System.out.println("-------------------------------");
        mundial.getPilotoDAO().deleteById(21);
        mundial.getPilotoDAO().findAll().forEach(System.out::println);

        mundial.close();
    }
}
