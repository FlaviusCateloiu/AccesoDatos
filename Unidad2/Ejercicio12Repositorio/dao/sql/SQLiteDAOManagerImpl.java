package Ejercicio12Repositorio.dao.sql;

import Ejercicio12Repositorio.dao.*;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDAOManagerImpl implements DAOManager {
    final String RUTABASE = Path.of("Unidad2/Ejercicio12Repositorio/db/f1-2023.db").toString();

    private Connection conexion;
    private EscuderiaDAO escuderias = null;
    private PilotoDAO pilotos = null;
    private CircuitoDAO circuitos = null;
    private ResultadosDAO resultados = null;
    // ...

    public SQLiteDAOManagerImpl() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + RUTABASE);
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        try {
            conexion.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            try {
                conexion.rollback();
                System.out.println("Se hace ROLLBACK");
            } catch (SQLException ex) {
                System.out.println("Error haciendo ROLLBACK");
            }
        }
    }

    public void commit() {
        try {
            conexion.commit();
        } catch (SQLException e) {
            try {
                conexion.rollback();
                System.out.println("Se hace ROLLBACK");
            } catch (SQLException ex) {
                System.out.println("Error haciendo ROLLBACK");
            }
        }
    }

    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public EscuderiaDAO getEscuderiaDAO() {
        if (escuderias == null) {
            escuderias = new SQLiteEscuderiaDAOImpl(conexion);
        }
        return escuderias;
    }

    @Override
    public PilotoDAO getPilotoDAO() {
        if (pilotos == null) {
            pilotos = new SQLitePilotoDAOImpl(conexion, (SQLiteEscuderiaDAOImpl) escuderias);
        }
        return pilotos;
    }

    @Override
    public CircuitoDAO getCircuitoDAO() {
        if (circuitos == null) {
            circuitos = new SQLiteCircuitoDAOImpl(conexion);
        }
        return circuitos;
    }

    @Override
    public ResultadosDAO getResultadosDAO() {
        if (resultados == null) {
            resultados = new SQLiteResultadosDAOImpl(conexion, (SQLitePilotoDAOImpl) pilotos, (SQLiteCircuitoDAOImpl) circuitos);
        }
        return resultados;
    }

}
