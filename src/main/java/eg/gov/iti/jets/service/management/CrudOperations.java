package eg.gov.iti.jets.service.management;

import java.util.List;

/**
 * Basic crud operations
 * @param <T>
 */
public interface CrudOperations<T> {
    Boolean create( T type);

    T update( T type);

    Boolean delete( int id);

    List<T> getAll();

    T getById( int id );

}
