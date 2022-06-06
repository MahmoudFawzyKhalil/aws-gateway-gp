package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.entity.aws.Ami;
import org.springframework.data.jpa.repository.JpaRepository;

interface AmiRepo extends JpaRepository<Ami,Integer> {
}
