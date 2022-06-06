package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import org.springframework.data.jpa.repository.JpaRepository;

interface KeyPairRepo extends JpaRepository<KeyPair,Integer> {
}
