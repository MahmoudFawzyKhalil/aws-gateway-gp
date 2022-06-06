package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InstanceDaoImpl implements InstanceDao {

    private InstanceRepo instanceRepo;

    public InstanceDaoImpl(InstanceRepo instanceRepo) {
        this.instanceRepo = instanceRepo;
    }

    @Override
    public Instance save(Instance instance) {
        return instanceRepo.save(instance);
    }

    @Override
    public Instance update(Instance instance) {
        return instanceRepo.save(instance);
    }

    @Override
    public Optional<Instance> findById(Integer id) {
        return instanceRepo.findById(id);
    }

    @Override
    public <C> Optional<Instance> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<Instance> findAll() {
        return instanceRepo.findAll();
    }

    @Override
    public List<Instance> findAll(int pageNumber, int pageSize) {
        Page<Instance> instancePage = instanceRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return instancePage.toList();
    }

    @Override
    public <C> List<Instance> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<Instance> findAllByExample(Instance example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return instanceRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<Instance> findAllByExample(Instance example, Class<C> projection) {
        return null;
    }
}
