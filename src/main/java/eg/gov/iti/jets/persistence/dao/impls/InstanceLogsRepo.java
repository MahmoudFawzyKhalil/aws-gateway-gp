package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import org.springframework.data.jpa.repository.JpaRepository;

interface InstanceLogsRepo extends JpaRepository<InstanceLogs,Long> {
}
