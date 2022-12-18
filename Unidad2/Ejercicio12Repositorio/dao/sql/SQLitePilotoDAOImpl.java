package Ejercicio12Repositorio.dao.sql;

import Ejercicio12Repositorio.dao.*;
import Ejercicio12Repositorio.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SQLitePilotoDAOImpl implements PilotoDAO {
    final String FINDALL = "SELECT * FROM Drivers";
    final String FINDBYID = "SELECT * FROM Drivers WHERE id = ?";
    final String SAVE = "INSERT INTO Drivers (name, birth_date, team, car_number) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE Drivers SET name = ?, birth_date = ?, team = ?, car_number = ? WHERE id = ?";
    final String DELETE = "DELETE FROM Drivers WHERE id = ?";

    private Connection conexion = null;
    private SQLiteEscuderiaDAOImpl escuderias;

    public SQLitePilotoDAOImpl(Connection conexion, SQLiteEscuderiaDAOImpl escuderias) {
        this.conexion = conexion;
        this.escuderias = escuderias;
    }

    @Override
    public List<Piloto> findAll() {
        List<Piloto> pilotos = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                pilotos.add(convertToPiloto(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return pilotos;
    }

    @Override
    public Piloto findById(Integer id) {
        Piloto piloto = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                piloto = convertToPiloto(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return piloto;
    }

    @Override
    public void save(Piloto piloto) {
        try (PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setInt(1, piloto.getNumero());
            sentencia.setString(2, piloto.getNombre());
            sentencia.setString(3, piloto.getFechaNacimiento().toString() + " 00:00:00");
            sentencia.setString(4, piloto.getEquipo().getNombre());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Piloto piloto) {
        try (PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setString(1, piloto.getNombre());
            sentencia.setString(2, piloto.getFechaNacimiento().toString() + " 00:00:00");
            sentencia.setString(3, piloto.getEquipo().getNombre());
            sentencia.setInt(4, piloto.getNumero());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement sentencia = conexion.prepareStatement(DELETE)) {
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private Piloto convertToPiloto(ResultSet rs) throws SQLException {
        Escuderia escuderia;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        escuderia = escuderias.findById(rs.getInt("id"));

        return new Piloto(rs.getString("name"), rs.getInt("car_number"),
                rs.getInt("id"), escuderia,
                LocalDate.parse(rs.getString("birth_date"), formatter));
    }
}
