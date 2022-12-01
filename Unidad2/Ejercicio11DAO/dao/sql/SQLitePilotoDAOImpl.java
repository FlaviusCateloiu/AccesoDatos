package Ejercicio11DAO.dao.sql;

import Ejercicio11DAO.dao.PilotoDAO;
import Ejercicio11DAO.models.Escuderia;
import Ejercicio11DAO.models.Piloto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SQLitePilotoDAOImpl implements PilotoDAO {
    final String FINDALL = "SELECT * FROM Drivers";
    final String FINDBYID = "SELECT * FROM Drivers WHERE DriverID = ?";
    final String SAVE = "INSERT INTO Drivers (DriverID, Name, DateOfBirth, Team) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE Drivers SET Name = ?, DateOfBirth = ?, Team = ? WHERE DriverID = ?";
    final String DELETE = "DELETE FROM Drivers WHERE DriverID = ?";

    private Connection conexion = null;
    private List<Escuderia> escuderias;

    public SQLitePilotoDAOImpl(Connection conexion, List<Escuderia> escuderias) {
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
            sentencia.setString(3, piloto.getFechaNacimientoString());
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
            sentencia.setString(2, piloto.getFechaNacimientoString());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Escuderia esc = null;
        String escuderia = rs.getString("team");

        for (Escuderia e : escuderias) {
            if (e.getNombre().equalsIgnoreCase(escuderia)) {
                esc = e;
            }
        }

        return new Piloto(rs.getString("name"), rs.getInt("driverID"), esc, LocalDate.parse(rs.getString("dateofbirth"), formatter));
    }
}
