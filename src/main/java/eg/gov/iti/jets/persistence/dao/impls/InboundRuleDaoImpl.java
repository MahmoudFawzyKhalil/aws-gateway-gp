package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.InboundRuleDao;
import eg.gov.iti.jets.persistence.entity.aws.InboundRule;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InboundRuleDaoImpl implements InboundRuleDao {
    private final InboundRuleRepo inboundRuleRepo;

    public InboundRuleDaoImpl(InboundRuleRepo inboundRuleRepo){
        this.inboundRuleRepo=inboundRuleRepo;
    }
    @Override
    public InboundRule save(InboundRule entity) {
        return inboundRuleRepo.save(entity);
    }

    @Override
    public InboundRule update(InboundRule entity) {
        if (entity == null || entity.getId() == null) {
            throw new NullPointerException("entity or id can't be null");
        }
        return inboundRuleRepo.save(entity);
    }

    @Override
    public Optional<InboundRule> findById(Integer id) {
        return inboundRuleRepo.findById(id);
    }

    @Override
    public <C> Optional<C> findById(Integer integer, Class<C> projection) {
        return inboundRuleRepo.findById(integer,projection);
    }

    @Override
    public List<InboundRule> findAll() {
        return inboundRuleRepo.findAll();
    }

    @Override
    public List<InboundRule> findAll(int pageNumber, int pageSize) {
        Page<InboundRule> inboundRulesPage = inboundRuleRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return inboundRulesPage.getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        Page<C> page = inboundRuleRepo.findBy(PageRequest.of(pageNumber, pageSize),projection);
        return page.getContent();
    }

    @Override
    public List<InboundRule> findAllByExample(InboundRule example) {
        ExampleMatcher caseInsensitiveExampleMatcher=ExampleMatcher.matchingAll().withIgnoreCase();
        return inboundRuleRepo.findAll(Example.of(example,caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return inboundRuleRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher),projection);
    }
}
