package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.service.model.Privilege;
import eg.gov.iti.jets.service.model.User;

import java.util.List;

public interface PrivilegeManagement {
    Boolean addPrivilege( Privilege privilege );
    Boolean deletePrivilege( int id );
    List<Privilege> getAllPrivilege();
    Privilege getPrivilegeById( int id );
}
