package Ejercicio12Repositorio.repository.sql;

import Ejercicio12Repositorio.dao.CircuitoDAO;
import Ejercicio12Repositorio.dao.EscuderiaDAO;
import Ejercicio12Repositorio.dao.PilotoDAO;
import Ejercicio12Repositorio.dao.ResultadosDAO;
import Ejercicio12Repositorio.dao.sql.SQLiteCircuitoDAOImpl;
import Ejercicio12Repositorio.dao.sql.SQLitePilotoDAOImpl;
import Ejercicio12Repositorio.dao.sql.SQLiteResultadosDAOImpl;
import Ejercicio12Repositorio.repository.*;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteRepositoryManagerImpl implements RepositoryManager {

    final String RUTABASE = Path.of("Unidad2/Ejercicio12Repositorio/db/f1-2023.db").toString();

    private Connection conexion;
    private EscuderiaRepository escuderias = null;
    private PilotoRepository pilotos = null;
    private CircuitoRepository circuitos = null;
    private ResultadosRepository resultados = null;
    // ...

    public SQLiteRepositoryManagerImpl() {
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
    public EscuderiaRepository getEscuderiaRepository() {
        return null;
    }

    @Override
    public PilotoRepository getPilotoRepository() {
        return null;
    }

    @Override
    public CircuitoRepository getCircuitoRepository() {
        return null;
    }

    @Override
    public ResultadosRepository getResultadosRepository() {
        return null;
    }
}
