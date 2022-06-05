package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InstanceLogsDaoImpl implements InstanceLogsDao {
    private final InstanceLogsRepo instanceLogsRepo;

    public InstanceLogsDaoImpl(InstanceLogsRepo instanceLogsRepo) {
        this.instanceLogsRepo = instanceLogsRepo;
    }


    @Override
    public InstanceLogs save(InstanceLogs entity) {
        return null;
    }

    @Override
    public InstanceLogs update(InstanceLogs entity) {
        return null;
    }

    @Override
    public Optional<InstanceLogs> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<InstanceLogs> findAll() {
        return null;
    }

    @Override
    public List<InstanceLogs> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<InstanceLogs> findAllByExample(InstanceLogs example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return instanceLogsRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }
}
