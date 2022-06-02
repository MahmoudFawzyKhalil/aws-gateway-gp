package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.RoleDao;
import eg.gov.iti.jets.persistence.entity.Role;

import java.util.List;
import java.util.Optional;

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
}
