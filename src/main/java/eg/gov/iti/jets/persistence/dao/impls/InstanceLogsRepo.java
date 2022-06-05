package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

interface InstanceLogsRepo extends JpaRepository<InstanceLogs, Long> {

    Optional<InstanceLogs> findFirstByActionAndInstance_IdOrderByDateTimeDesc(UserAction action,int id);

    boolean deleteLogsByDateTimeLessThan(LocalDateTime startDate);

    Page<InstanceLogs> findAllByInstanceId(Long id, Pageable pageable);

    Page<InstanceLogs> findAllByActionMakerAndAction(int id, UserAction userAction, Pageable pageable);
}
