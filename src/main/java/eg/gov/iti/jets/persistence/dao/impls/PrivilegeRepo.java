package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

interface PrivilegeRepo extends JpaRepository<Privilege,Integer> {
}
