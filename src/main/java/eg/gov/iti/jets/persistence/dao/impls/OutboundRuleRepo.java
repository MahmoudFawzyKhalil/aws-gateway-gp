package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.aws.OutboundRule;
import org.springframework.data.jpa.repository.JpaRepository;

interface OutboundRuleRepo extends JpaRepository<OutboundRule,Integer> {
}
