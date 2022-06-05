package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.UserManagement;
//import org.springframework.security.core.userdetails.User;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementImpl implements UserDetailsService, UserManagement {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * todo
         * first talk with repo to load user
         * user userAdapter to convert user entity to user details
         */

        switch (username){
            case "hesham":
                User user = new User();
                user.setUsername("hesham");
                user.setPassword("1234");
                Privilege privilege = new Privilege();
                privilege.setName("WRITE");
                Privilege privilege2 = new Privilege();
                privilege2.setName("START_STOP_INSTANCE");
                Role role = new Role();
                role.setPrivileges(List.of(privilege, privilege2));
                user.setRole(role);
                return new UserAdapter(user);
            default:
                User user1 = new User();
                user1.setUsername("ashraf");
                user1.setPassword("1234");
                Privilege privilege1 = new Privilege();
                privilege1.setName("READ");
                Role role1 = new Role();
                role1.setPrivileges(List.of(privilege1));
                user1.setRole(role1);
                return new UserAdapter(user1);
        }
    }

    @Override
    public Boolean createUser( User user ) {
        return null;
    }

    @Override
    public User updateUser( User user ) {
        return null;
    }

    @Override
    public Boolean deleteUser( int id ) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserById( int id ) {
        return null;
    }

    @Override
    public Boolean createUserFromCSV( String csvFile ) {
        return null;
    }
}
