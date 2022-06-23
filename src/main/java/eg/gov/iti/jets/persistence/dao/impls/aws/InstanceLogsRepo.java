package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface InstanceLogsRepo extends JpaRepository<InstanceLogs, Integer> {

    Optional<InstanceLogs> findFirstByActionAndInstance_IdOrderByDateTimeDesc(UserAction action, int id);

    boolean deleteLogsByDateTimeLessThan(LocalDateTime startDate);

    Page<InstanceLogs> findAllByInstanceIdOrderByDateTimeDesc(int id, Pageable pageable);

    Page<InstanceLogs> findAllByActionMaker_IdAndActionOrderByDateTimeDesc(int id, UserAction userAction, Pageable pageable);

    <C> Optional<C> findById(Integer id, Class<C> projection);

    <C> Page<C> findAllByActionMaker_IdOrderByDateTimeDesc(Integer userId, Pageable pageable, Class<C> projection);

    <C> Page<C> findAllByActionMaker_IdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime dateTime1, LocalDateTime dateTime2, Pageable pageable, Class<C> projection);

    <C> Page<C> findByOrderByDateTimeDesc(Pageable pageable, Class<C> projection);

    <C> List<C> findAllByOrderByDateTimeDesc(Example<C> example, Class<C> projection);


}
