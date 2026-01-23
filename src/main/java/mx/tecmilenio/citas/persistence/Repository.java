package mx.tecmilenio.citas.persistence;

import java.util.List;

public interface Repository<T> {
    void save(T item) throws Exception;
    List<T> findAll() throws Exception;
    boolean existsById(String id) throws Exception;
}
