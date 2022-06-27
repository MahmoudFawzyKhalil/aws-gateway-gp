package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;

import java.util.Optional;

public interface PrivilegeDao extends GenericCrudDao<Privilege, Integer> {
    Optional<Privilege> findByName(PrivilegeName privilegeName);
}
