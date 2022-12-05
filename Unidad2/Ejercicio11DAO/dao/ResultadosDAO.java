package Ejercicio11DAO.dao;

import Ejercicio11DAO.models.Resultados;

import java.util.List;

public interface ResultadosDAO {
    List<Resultados> findAll();
    Resultados findById(Integer pilotoId, Integer circuitoId);
    void save(Resultados resultados);
    void update (Resultados resultados);
    void deleteById (Integer pilotoId, Integer circuitoId);
}
