package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

interface RoleRepo extends JpaRepository<Role,Integer> {
}
