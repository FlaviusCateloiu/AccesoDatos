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

public class SQLiteResultadosDAOImpl implements ResultadosDAO {

    final String FINDALL = "SELECT * FROM Results";
    final String FINDBYID = "SELECT * FROM Results WHERE id = ?";
    final String SAVE = "INSERT INTO Results (points, position, driver_id, track_id) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE Results SET points = ?, position = ?, driver_id = ?, TrackID = ? WHERE id = ?";
    final String DELETE = "DELETE FROM Results WHERE id = ?";

    private Connection conexion = null;
    private SQLitePilotoDAOImpl pilotos;
    private SQLiteCircuitoDAOImpl circuitos;

    public SQLiteResultadosDAOImpl(Connection conexion, SQLitePilotoDAOImpl pilotos, SQLiteCircuitoDAOImpl circuitos) {
        this.conexion = conexion;
        this.pilotos = pilotos;
        this.circuitos = circuitos;
    }

    @Override
    public List<Resultados> findAll() {
        List<Resultados> resultados = new ArrayList<>();
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDALL);
             ResultSet rs = sentencia.executeQuery()){
            while (rs.next()) {
                resultados.add(convertirResultados(rs));
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return resultados;
    }

    @Override
    public Resultados findById(Integer id) {
        Resultados resultado = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                resultado = convertirResultados(rs);
            }
            rs.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return resultado;
    }

    @Override
    public void save(Resultados resultados) {
        try (PreparedStatement sentencia = conexion.prepareStatement(SAVE)) {
            sentencia.setInt(1, resultados.getPuntos());
            sentencia.setInt(2, resultados.getPosicion());
            sentencia.setInt(3, resultados.getPiloto().getNumero());
            sentencia.setInt(4, resultados.getCircuito().getNumero());
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void update(Resultados resultados) {
        try (PreparedStatement sentencia = conexion.prepareStatement(UPDATE)){
            sentencia.setInt(1, resultados.getPuntos());
            sentencia.setInt(2, resultados.getPosicion());
            sentencia.setInt(3, resultados.getPiloto().getNumero());
            sentencia.setInt(4, resultados.getCircuito().getNumero());
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

    private Resultados convertirResultados(ResultSet rs) throws SQLException {
        Piloto piloto;
        Circuito circuito;

        piloto = pilotos.findById(rs.getInt("id"));
        circuito = circuitos.findById(rs.getInt("id"));

        return new Resultados(rs.getInt("id"), piloto,
                circuito,
                rs.getInt("position"), rs.getInt("points"));
    }
}
