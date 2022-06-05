package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

interface InstanceLogsRepo extends JpaRepository<InstanceLogs,Long> {
    @Query("select i.dateTime from InstanceLogs i where i.action = 'stop' and i.instance = :instance")
    LocalDateTime findLastStopDate(@Param("instance") Instance instance);
}
