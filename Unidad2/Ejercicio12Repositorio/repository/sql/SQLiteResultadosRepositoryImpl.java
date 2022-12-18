package Ejercicio12Repositorio.repository.sql;

import Ejercicio12Repositorio.dao.sql.SQLiteResultadosDAOImpl;
import Ejercicio12Repositorio.models.Resultados;
import Ejercicio12Repositorio.repository.ResultadosRepository;

import java.util.List;

public class SQLiteResultadosRepositoryImpl implements ResultadosRepository {
    private SQLiteResultadosDAOImpl resultadosDAOImpl;
    @Override
    public List<Resultados> findAll() {
        return resultadosDAOImpl.findAll();
    }

    @Override
    public Resultados get(Integer id) {
        Resultados resultados = resultadosDAOImpl.findById(id);
        return resultados;
    }

    @Override
    public void add(Resultados resultados) {
        resultadosDAOImpl.save(resultados);
    }

    @Override
    public void update(Resultados resultados) {
        resultadosDAOImpl.update(resultados);
    }

    @Override
    public void deleteById(Integer id) {
        resultadosDAOImpl.deleteById(id);
    }
}
