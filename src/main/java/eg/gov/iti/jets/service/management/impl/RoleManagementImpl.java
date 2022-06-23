package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.RoleDao;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.service.exception.ResourceConstraintsViolationException;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.RoleManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleManagementImpl implements RoleManagement {
    private final RoleDao roleDao;

    @Override
    public Role addRole( Role role ) {
       try {
           return roleDao.save(role);
       } catch (Exception e) {
           throw new ResourceConstraintsViolationException("Could not create role with id " + role.getId()+ ", privileges" + role.getPrivileges()
                   .stream().map(Privilege::getId).collect(Collectors.toList()));
       }
    }

    @Override
    public Boolean deleteRole(Integer id ) {
        return null;
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.findAll();
    }

    @Override
    public Role getRoleById(Integer id ) {
        return roleDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Role with id " + id + ", is not found"));
    }

    @Override
    public Role updateRole(Role role) {
        roleDao.findById(role.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Role with id " + role.getId() + ", is not found!")
        );
        try {
            return roleDao.update(role);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ResourceExistException("Could not update role with id " + role.getId());
        }
    }
}
