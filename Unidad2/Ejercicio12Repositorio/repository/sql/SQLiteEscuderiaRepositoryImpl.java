package Ejercicio12Repositorio.repository.sql;

import Ejercicio12Repositorio.dao.sql.SQLiteEscuderiaDAOImpl;
import Ejercicio12Repositorio.models.Escuderia;
import Ejercicio12Repositorio.repository.EscuderiaRepository;

import java.util.List;

public class SQLiteEscuderiaRepositoryImpl implements EscuderiaRepository {
    private SQLiteEscuderiaDAOImpl escuderiaDAOImpl;
    @Override
    public List<Escuderia> findAll() {
        return escuderiaDAOImpl.findAll();
    }

    @Override
    public Escuderia get(Integer id) {
        Escuderia escuderia = escuderiaDAOImpl.findById(id);
        return escuderia;
    }

    @Override
    public void add(Escuderia escuderia) {
        escuderiaDAOImpl.save(escuderia);
    }

    @Override
    public void update(Escuderia escuderia) {
        escuderiaDAOImpl.update(escuderia);
    }

    @Override
    public void deleteById(Integer id) {
        escuderiaDAOImpl.deleteById(id);
    }
}
