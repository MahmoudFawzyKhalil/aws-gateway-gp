package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.PrivilegeDao;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.PrivilegeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeManagementImpl implements PrivilegeManagement {
    private final PrivilegeDao privilegeDao;

    @Override
    public Boolean addPrivilege( Privilege privilege ) {
        try {
            privilegeDao.save(privilege);
            return true;
        }catch (Exception exception) {
            throw new RuntimeException("privilege already exists");
        }
    }

    @Override
    public Boolean deletePrivilege( int id ) {
        return null;
    }

    @Override
    public List<Privilege> getAllPrivilege() {
        return privilegeDao.findAll();
    }

    @Override
    public Privilege getPrivilegeById(Integer id ) {
       return privilegeDao.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Privilege with id " + id + ", is not found")
       );
    }
}
