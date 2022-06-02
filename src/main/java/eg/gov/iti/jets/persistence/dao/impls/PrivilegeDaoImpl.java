package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.PrivilegeDao;
import eg.gov.iti.jets.persistence.entity.Privilege;

import java.util.List;
import java.util.Optional;

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
        return privilegeRepo.save(entity);
    }

    @Override
    public Optional<Privilege> findById(Integer id) {
        return privilegeRepo.findById(id);
    }

    @Override
    public List<Privilege> findAll() {
        return privilegeRepo.findAll();
    }

    @Override
    public List<Privilege> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<Privilege> findAllByExample(Privilege example) {
        return null;
    }
}
