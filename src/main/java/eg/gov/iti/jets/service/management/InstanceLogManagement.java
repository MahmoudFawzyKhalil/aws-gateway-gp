package eg.gov.iti.jets.service.management;

import java.time.LocalDateTime;
import java.util.List;

public interface InstanceLogManagement {
    <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection);

    <C> List<C> findAllByUser(int userId, int pageNumber, int pageSize, Class<C> projection);

    <C> List<C> findAllByUserAndDate(int userId, int pageNumber, LocalDateTime dateTime1, LocalDateTime dateTime2, int pageSize, Class<C> projection);
}
