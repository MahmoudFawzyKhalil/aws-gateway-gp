package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.RoleDao;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.RoleManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleManagementImpl implements RoleManagement {
    private final RoleDao roleDao;

    @Override
    public Boolean addRole( Role role ) {
       try {
           roleDao.save(role);
           return true;
       }catch (Exception e) {
           throw new RuntimeException("Role already exist");
       }
    }

    @Override
    public Boolean deleteRole( int id ) {
        return null;
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.findAll();
    }

    @Override
    public Role getRoleById( int id ) {
        return roleDao.findById(id).orElseThrow(()->new RuntimeException("Not found role with id"));
    }
}
