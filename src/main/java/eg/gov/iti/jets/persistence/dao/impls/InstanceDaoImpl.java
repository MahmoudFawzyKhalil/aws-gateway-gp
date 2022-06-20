package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        if (instance == null || instance.getId() == null) {
            throw new NullPointerException("instance or id can't be null");
        }
        return instanceRepo.save(instance);
    }

    @Override
    public Optional<Instance> findById(Integer id) {
        return instanceRepo.findById(id);
    }

    @Override
    public <C> Optional<C> findById(Integer integer, Class<C> projection) {
        return instanceRepo.findById(integer, projection);
    }

    @Override
    public List<Instance> findAll() {
        return instanceRepo.findAll();
    }

    @Override
    public List<Instance> findAll(int pageNumber, int pageSize) {
        return instanceRepo.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return instanceRepo.findBy(PageRequest.of(pageNumber, pageSize), projection).getContent();
    }

    @Override
    public List<Instance> findAllByExample(Instance example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return instanceRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return instanceRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher), projection);
    }

    @Override
    public List<Instance> findUserGrantedInstances(int userId) {
        return instanceRepo.findAllByInstanceUsers_id(userId);
    }

    @Override
    public List<Instance> findFollowersUsersGrantedInstances(int userId) {
        return instanceRepo.findAllDistinctByInstanceUsers_manager_id(userId);
    }
}
