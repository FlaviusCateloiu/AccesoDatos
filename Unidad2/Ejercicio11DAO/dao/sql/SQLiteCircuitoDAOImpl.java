package Ejercicio11DAO.dao.sql;

import Ejercicio11DAO.dao.CircuitoDAO;
import Ejercicio11DAO.models.Circuito;
import Ejercicio11DAO.models.Piloto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SQLiteCircuitoDAOImpl implements CircuitoDAO {
    final String FINDALL = "SELECT * FROM Tracks";
    final String FINDBYID = "SELECT * FROM Tracks WHERE TrackID = ?";
    final String SAVE = "INSERT INTO Tracks (TrackID, Country, DateOfRace) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE Tracks SET Country = ?, DateOfRace = ? WHERE TrackID = ?";
    final String DELETE = "DELETE FROM Tracks WHERE TrackID = ?";

    private Connection conexion = null;

    public SQLiteCircuitoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Circuito> findAll() {
        List<Circuito> circuitos = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                circuitos.add(convertToCircuito(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return circuitos;
    }

    @Override
    public Circuito findById(Integer id) {
        Circuito circuito = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                circuito = convertToCircuito(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return circuito;
    }

    @Override
    public void save(Circuito circuito) {
        try (PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setInt(1, circuito.getNumero());
            sentencia.setString(2, circuito.getNombre());
            sentencia.setString(3, circuito.getFechaCircuito().toString() + " 00:00:00");
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Circuito circuito) {
        try (PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setString(1, circuito.getNombre());
            sentencia.setString(2, circuito.getFechaCircuito().toString() + " 00:00:00");
            sentencia.setInt(3, circuito.getNumero());
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

    private Circuito convertToCircuito(ResultSet rs) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new Circuito(rs.getInt("trackid"), rs.getString("country"), LocalDate.parse(rs.getString("dateofrace"), formatter));
    }
}
