package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InstanceLogsDao extends GenericCrudDao<InstanceLogs,Long> {

    Optional<InstanceLogs> findLastLogByAction(UserAction action);
    boolean deleteInstanceLogByDate(LocalDateTime localDateTime);
}
