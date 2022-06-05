package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface InstanceLogsRepo extends JpaRepository<InstanceLogs,Long> {

    Optional<InstanceLogs> findFirstByActionOrderByDateTimeDesc(UserAction action);
    boolean deleteLogsByDateTimeLessThan(LocalDateTime startDate);
}
