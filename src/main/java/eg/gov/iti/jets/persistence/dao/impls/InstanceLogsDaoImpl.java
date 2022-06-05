package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.persistence.dao.KeyPairDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
        return instanceLogsRepo.save(entity);
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
        Page<InstanceLogs> instanceLogPage = instanceLogsRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return instanceLogPage.toList();
    }

    @Override
    public List<InstanceLogs> findAllByExample(InstanceLogs example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return instanceLogsRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }


    @Override
    public Optional<InstanceLogs> findLastLogByAction(UserAction action) {
        return instanceLogsRepo.findFirstByActionOrderByDateTimeDesc(action);
    }

    @Override
    public boolean deleteInstanceLogByDate(LocalDateTime localDateTime) {
        return instanceLogsRepo.deleteLogsByDateTimeLessThan(localDateTime);
    }
}
