package eg.gov.iti.jets.service.management;


import eg.gov.iti.jets.persistence.entity.Role;

import java.util.List;

public interface RoleManagement {
    Role addRole( Role role );
    Boolean deleteRole( Integer id );
    List<Role> getAllRole();
    Role getRoleById( Integer id );
    Role updateRole(Role role);
}
