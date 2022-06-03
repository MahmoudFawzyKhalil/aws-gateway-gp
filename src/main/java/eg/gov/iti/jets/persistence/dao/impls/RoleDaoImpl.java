package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.RoleDao;
import eg.gov.iti.jets.persistence.entity.Role;
import org.springframework.data.domain.Example;
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
    public Role update(Role roleEntity) {
        return roleRepo.save(roleEntity);
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepo.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public List<Role> findAll(int pageNumber, int pageSize) {
        return (List<Role>) roleRepo.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    public List<Role> findAllByExample(Role example) {
        return (List<Role>) roleRepo.findAll(Example.of(example));
    }
}
