package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

interface BranchRepo extends JpaRepository<Branch,Integer> {
}
