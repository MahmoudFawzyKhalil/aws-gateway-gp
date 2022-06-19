package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.RoleDao;
import eg.gov.iti.jets.persistence.entity.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final RoleRepo roleRepo;

    public RoleDaoImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role save(Role roleEntity) {
        return roleRepo.save(roleEntity);
    }

    @Override
    public Role update(Role entity) {
        if(entity == null || entity.getId() == null) throw new NullPointerException("Role id can't be null");
        return roleRepo.save(entity);
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepo.findById(id);
    }

    @Override
    public <C> Optional<C> findById(Integer id, Class<C> projection) {
        return roleRepo.findById(id,projection);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public List<Role> findAll(int pageNumber, int pageSize) {
        Page<Role> rolePage = roleRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return rolePage.getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return roleRepo.findBy(PageRequest.of(pageNumber,pageSize),projection).getContent();
    }

    @Override
    public List<Role> findAllByExample(Role example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return roleRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return roleRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher),projection);
    }
}
