package Ejercicio12Repositorio.repository.sql;

import Ejercicio12Repositorio.dao.sql.SQLitePilotoDAOImpl;
import Ejercicio12Repositorio.models.Piloto;
import Ejercicio12Repositorio.repository.PilotoRepository;

import java.util.List;

public class SQLitePilotoRepositoryImpl implements PilotoRepository {
    private SQLitePilotoDAOImpl pilotoDAOImpl;
    @Override
    public List<Piloto> findAll() {
        return pilotoDAOImpl.findAll();
    }

    @Override
    public Piloto get(Integer id) {
        Piloto piloto = pilotoDAOImpl.findById(id);
        return piloto;
    }

    @Override
    public void add(Piloto piloto) {
        pilotoDAOImpl.save(piloto);
    }

    @Override
    public void update(Piloto piloto) {
        pilotoDAOImpl.update(piloto);
    }

    @Override
    public void deleteById(Integer id) {
        pilotoDAOImpl.deleteById(id);
    }
}
