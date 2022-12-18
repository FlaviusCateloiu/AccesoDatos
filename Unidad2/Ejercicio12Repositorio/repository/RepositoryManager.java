package Ejercicio12Repositorio.repository;

import Ejercicio12Repositorio.dao.CircuitoDAO;
import Ejercicio12Repositorio.dao.ResultadosDAO;

public interface RepositoryManager {
    EscuderiaRepository getEscuderiaRepository();
    PilotoRepository getPilotoRepository();
    CircuitoRepository getCircuitoRepository();
    ResultadosRepository getResultadosRepository();
}
