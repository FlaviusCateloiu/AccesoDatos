package Ejercicio11DAO.dao.sql;

import Ejercicio11DAO.dao.ResultadosDAO;
import Ejercicio11DAO.models.Piloto;
import Ejercicio11DAO.models.Resultados;

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
    final String FINDBYID = "SELECT * FROM Results WHERE DriverID = ? AND TrackID = ?";
    final String SAVE = "INSERT INTO Results (Points, Position, DriverID, TrackID) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE Results SET Points = ?, Position = ? WHERE DriverID = ? AND TrackID = ?";
    final String DELETE = "DELETE FROM Results WHERE DriverID = ? AND TrackID = ?";

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
    public Resultados findById(Integer pilotoId, Integer circuitoId) {
        Resultados resultado = null;
        try (PreparedStatement sentencia = conexion.prepareStatement(FINDBYID)) {
            sentencia.setInt(1, pilotoId);
            sentencia.setInt(2, circuitoId);
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
    public void deleteById(Integer pilotoId, Integer circuitoId) {
        try (PreparedStatement sentencia = conexion.prepareStatement(DELETE)) {
            sentencia.setInt(1, pilotoId);
            sentencia.setInt(2, circuitoId);
            sentencia.executeUpdate();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private Resultados convertirResultados(ResultSet rs) throws SQLException {
        return new Resultados(pilotos.findById(rs.getInt("driverid")), circuitos.findById(rs.getInt("trackid")), rs.getInt("position"), rs.getInt("points"));
    }
}
