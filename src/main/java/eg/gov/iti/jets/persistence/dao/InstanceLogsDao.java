package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InstanceLogsDao extends GenericCrudDao<InstanceLogs, Integer> {

    Optional<InstanceLogs> findLastLogByActionAndInstanceId(UserAction action,int instanceId);
    boolean deleteInstanceLogsBeforeDate(LocalDateTime localDateTime);
    List<InstanceLogs> findAllByInstanceId(int instanceId, int pageNumber, int pageSize);
    List<InstanceLogs> findAllByActionMakerIdAndAction(int id, UserAction userAction, int pageNumber, int pageSize);

}
