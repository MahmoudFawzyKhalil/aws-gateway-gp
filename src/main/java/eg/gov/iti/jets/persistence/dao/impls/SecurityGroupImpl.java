package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

public class SecurityGroupImpl implements SecurityGroupDao {

    private SecurityGroupRepo securityGroupRepo;

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
    public List<SecurityGroup> findAll() {
        return securityGroupRepo.findAll();
    }

    @Override
    public List<SecurityGroup> findAll(int pageNumber, int pageSize) {
        Page<SecurityGroup> securityGroupPage = securityGroupRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return securityGroupPage.toList();
    }

    @Override
    public List<SecurityGroup> findAllByExample(SecurityGroup example) {
        return securityGroupRepo.findAll(Example.of(example));
    }
}
