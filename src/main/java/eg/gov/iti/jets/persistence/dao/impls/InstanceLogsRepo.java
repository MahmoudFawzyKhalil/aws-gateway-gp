package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDateTime;

interface InstanceLogsRepo extends JpaRepository<InstanceLogs,Long> {
    @Query("select i.dateTime from InstanceLogs i where i.action = 'stop' and i.instance = :instance")
    LocalDateTime findLastStopDate(@Param("instance") Instance instance);

    List<InstanceLogs> findAllByAction_TerminateInstance(String terminateInstance);

    @Query( value = "select * from instance_logs where id = :id and dateTime = (select max(dateTime) from instance_logs group by id having id = :id) " ,nativeQuery = true)
    InstanceLogs findLatestTerminateInstanceById(@Param("id") Long instanceId);


}
