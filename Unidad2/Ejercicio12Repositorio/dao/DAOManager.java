package Ejercicio12Repositorio.dao;

public interface DAOManager {
    EscuderiaDAO getEscuderiaDAO();
    PilotoDAO getPilotoDAO();
    CircuitoDAO getCircuitoDAO();
    ResultadosDAO getResultadosDAO();
    // ...
}