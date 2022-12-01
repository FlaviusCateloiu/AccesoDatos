package Ejercicio11DAO.dao.sql;
import Ejercicio11DAO.dao.DAOManager;
import Ejercicio11DAO.dao.EscuderiaDAO;
import Ejercicio11DAO.dao.PilotoDAO;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDAOManagerImpl implements DAOManager {
    final String RUTABASE = Path.of("Unidad2/Ejercicio11DAO/db/formula1.db").toString();

    private Connection conexion;

    private EscuderiaDAO escuderias = null;
    private PilotoDAO pilotos = null;
    // ...

    public SQLiteDAOManagerImpl() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + RUTABASE);
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
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
            pilotos = new SQLitePilotoDAOImpl(conexion, escuderias.findAll());
        }
        return pilotos;
    }


}
