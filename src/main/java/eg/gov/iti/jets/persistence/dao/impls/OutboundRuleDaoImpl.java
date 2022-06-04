package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.OutboundRuleDao;
import eg.gov.iti.jets.persistence.entity.aws.OutboundRule;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OutboundRuleDaoImpl implements OutboundRuleDao {
    private final OutboundRuleRepo outboundRuleRepo;

    public OutboundRuleDaoImpl(OutboundRuleRepo outboundRuleRepo){
        this.outboundRuleRepo = outboundRuleRepo;
    }
    @Override
    public OutboundRule save(OutboundRule entity) {
        return outboundRuleRepo.save(entity);
    }

    @Override
    public OutboundRule update(OutboundRule entity) {
        return outboundRuleRepo.save(entity);
    }

    @Override
    public Optional<OutboundRule> findById(Integer id) {
        return outboundRuleRepo.findById(id);
    }

    @Override
    public List<OutboundRule> findAll() {
        return outboundRuleRepo.findAll();
    }

    @Override
    public List<OutboundRule> findAll(int pageNumber, int pageSize) {
        Page<OutboundRule> amiPage = outboundRuleRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return amiPage.getContent();
    }

    @Override
    public List<OutboundRule> findAllByExample(OutboundRule example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return outboundRuleRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }
}
