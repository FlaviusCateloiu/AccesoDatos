package Ejercicio12Repositorio.dao.sql;

import Ejercicio12Repositorio.dao.*;
import Ejercicio12Repositorio.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteEscuderiaDAOImpl implements EscuderiaDAO {

    final String FINDALL = "SELECT * FROM Teams";
    final String FINDBYID = "SELECT * FROM Teams WHERE id = ?";
    final String SAVE = "INSERT INTO Teams (name, chassis, power_unit) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE Teams SET name = ?, chassis = ?, power_unit = ? WHERE id = ?";
    final String DELETE = "DELETE FROM Teams WHERE id = ?";

    private Connection conexion = null;

    public SQLiteEscuderiaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Escuderia> findAll() {
        List<Escuderia> escuderias = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                escuderias.add(convertToEscuderia(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return escuderias;
    }

    @Override
    public Escuderia findById(Integer id) {
        Escuderia escuderia = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                escuderia = convertToEscuderia(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return escuderia;
    }

    @Override
    public void save(Escuderia escuderia) {
        try (PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setString(1, escuderia.getNombre());
            sentencia.setString(2, escuderia.getChasis());
            sentencia.setString(3, escuderia.getMotor());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Escuderia escuderia) {
        try (PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setString(1, escuderia.getNombre());
            sentencia.setString(2, escuderia.getChasis());
            sentencia.setString(3, escuderia.getMotor());
            sentencia.setInt(4, escuderia.getNumero());
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

    private Escuderia convertToEscuderia(ResultSet rs) throws SQLException {
        return new Escuderia(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("chassis"),
                rs.getString("power_unit"));
    }
}
