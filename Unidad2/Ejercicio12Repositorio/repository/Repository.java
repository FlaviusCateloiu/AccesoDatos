package Ejercicio12Repositorio.repository;

import java.util.List;

public interface Repository<T, K> {
    List<T> findAll();
    T get(K id);
    void add(T t);
    void update (T t);
    void deleteById (K id);
}
