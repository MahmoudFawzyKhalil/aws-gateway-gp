package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

interface SecurityGroupRepo extends JpaRepository<SecurityGroup,Integer> {
}
