package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.entity.aws.InboundRule;
import org.springframework.data.jpa.repository.JpaRepository;

interface InboundRuleRepo extends JpaRepository<InboundRule,Integer> {
}
