package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.PrivilegeDao;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrivilegeDaoImpl implements PrivilegeDao {
    private final PrivilegeRepo privilegeRepo;

    public PrivilegeDaoImpl(PrivilegeRepo privilegeRepo) {
        this.privilegeRepo = privilegeRepo;
    }

    @Override
    public Privilege save(Privilege entity) {
        return privilegeRepo.save(entity);
    }

    @Override
    public Privilege update(Privilege entity) {
        if (entity == null || entity.getId() == null) {
            throw new NullPointerException("entity or id can't be null");
        }
        return privilegeRepo.save(entity);
    }

    @Override
    public Optional<Privilege> findById(Integer id) {
        return privilegeRepo.findById(id);
    }

    @Override
    public <C> Optional<C> findById(Integer id, Class<C> projection) {
        return privilegeRepo.findById(id, projection);
    }

    @Override
    public List<Privilege> findAll() {
        return privilegeRepo.findAll();
    }

    @Override
    public List<Privilege> findAll(int pageNumber, int pageSize) {
        Page<Privilege> privilegePage = privilegeRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return privilegePage.getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        Page<C> page = privilegeRepo.findBy(PageRequest.of(pageNumber, pageSize),projection);
        return page.getContent();
    }

    @Override
    public List<Privilege> findAllByExample(Privilege example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return privilegeRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return privilegeRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher),projection);
    }

    @Override
    public Optional<Privilege> findByName(PrivilegeName privilegeName) {
        return privilegeRepo.findByName(privilegeName);
    }
}
