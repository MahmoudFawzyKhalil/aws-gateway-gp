package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;

import java.util.List;
import java.time.LocalDateTime;

public interface InstanceLogsDao extends GenericCrudDao<InstanceLogs,Long> {

    LocalDateTime findLastStopDate(Instance instance);

     List<InstanceLogs> findAllByAction_TerminateInstance(String terminateInstance);
     InstanceLogs findLatestTerminateInstanceById(Long instanceId);

}
