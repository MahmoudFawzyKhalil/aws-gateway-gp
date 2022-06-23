package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.Role;
import java.util.Optional;

public interface RoleDao extends GenericCrudDao<Role,Integer> {
    Optional<Role> findRoleByName(String name);
}

