package eg.gov.iti.jets.service.management;


import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.User;

import java.util.List;

public interface RoleManagement {
    Boolean addRole( Role role );
    Boolean deleteRole( int id );
    List<Role> getAllRole();
    Role getRoleById( int id );
    Boolean updateRole(Role role);
}
