package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import org.springframework.data.jpa.repository.JpaRepository;

interface InstanceRepo extends JpaRepository<Instance,Integer> {
}
