package Ejercicio12Repositorio.repository.sql;

import Ejercicio12Repositorio.dao.sql.SQLiteCircuitoDAOImpl;
import Ejercicio12Repositorio.models.Circuito;
import Ejercicio12Repositorio.repository.CircuitoRepository;

import java.util.List;

public class SQLiteCircuitoRepositoryImpl implements CircuitoRepository {
    private SQLiteCircuitoDAOImpl circuitoDaoImpl;
    @Override
    public List<Circuito> findAll() {
        return circuitoDaoImpl.findAll();
    }

    @Override
    public Circuito get(Integer id) {
        Circuito circuito = circuitoDaoImpl.findById(id);
        return circuito;
    }

    @Override
    public void add(Circuito circuito) {
        circuitoDaoImpl.save(circuito);
    }

    @Override
    public void update(Circuito circuito) {
        circuitoDaoImpl.update(circuito);
    }

    @Override
    public void deleteById(Integer id) {
        circuitoDaoImpl.deleteById(id);
    }
}
