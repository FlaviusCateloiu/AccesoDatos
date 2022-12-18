package Ejercicio12Repositorio.repository.sql;

import Ejercicio12Repositorio.dao.sql.SQLiteResultadosDAOImpl;
import Ejercicio12Repositorio.models.Resultados;
import Ejercicio12Repositorio.repository.ResultadosRepository;

import java.sql.Connection;
import java.util.List;

public class SQLiteResultadosRepositoryImpl implements ResultadosRepository {
    private SQLiteResultadosDAOImpl resultadosDAOImpl;
    private Connection conexion = null;

    public SQLiteResultadosRepositoryImpl(Connection conexion) {
        this.conexion = conexion;
    }
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
