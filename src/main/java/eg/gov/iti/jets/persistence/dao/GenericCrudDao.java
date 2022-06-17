package eg.gov.iti.jets.persistence.dao;

import java.util.List;
import java.util.Optional;

interface GenericCrudDao<T, Id> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(Id id);

    <C> Optional<C> findById(Id id, Class<C> projection);

    List<T> findAll();

    List<T> findAll(int pageNumber, int pageSize);

    <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection);

    List<T> findAllByExample(T example);

    <C> List<C> findAllByExample(C example, Class<C> projection);


}
