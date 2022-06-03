package eg.gov.iti.jets.api.util;

import java.util.List;

/**
 * Basic crud operations
 * @param <T>
 */
public interface Crud<T> {
    Boolean create( T type);

    T update( T type);

    Boolean delete( int id);

    List<T> getAll();

    T getById( int id );

}
