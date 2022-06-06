package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SecurityGroupDaoImpl implements SecurityGroupDao {

    private SecurityGroupRepo securityGroupRepo;

    public SecurityGroupDaoImpl(SecurityGroupRepo securityGroupRepo) {
        this.securityGroupRepo = securityGroupRepo;
    }

    @Override
    public SecurityGroup save(SecurityGroup securityGroup) {
        return securityGroupRepo.save(securityGroup);
    }

    @Override
    public SecurityGroup update(SecurityGroup securityGroup) {
        return securityGroupRepo.save(securityGroup);
    }

    @Override
    public Optional<SecurityGroup> findById(Integer id) {
        return securityGroupRepo.findById(id);
    }

    @Override
    public <C> Optional<SecurityGroup> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<SecurityGroup> findAll() {
        return securityGroupRepo.findAll();
    }

    @Override
    public List<SecurityGroup> findAll(int pageNumber, int pageSize) {
        Page<SecurityGroup> securityGroupPage = securityGroupRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return securityGroupPage.toList();
    }

    @Override
    public <C> List<SecurityGroup> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<SecurityGroup> findAllByExample(SecurityGroup example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return securityGroupRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<SecurityGroup> findAllByExample(SecurityGroup example, Class<C> projection) {
        return null;
    }
}
