package Ejercicio11DAO.dao;

public interface DAOManager {
    EscuderiaDAO getEscuderiaDAO();
    PilotoDAO getPilotoDAO();
    CircuitoDAO getCircuitoDAO();
    // ...
}