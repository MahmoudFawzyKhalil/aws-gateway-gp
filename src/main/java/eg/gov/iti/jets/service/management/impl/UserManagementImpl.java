package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementImpl implements UserDetailsService, UserManagement {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        switch (username){
            case "hesham":
                var user = org.springframework.security.core.userdetails.User.withUsername("hesham")
                        .password("1234")
                        .authorities("WRITE")
                        .build();
                return user;
            default:
                user = org.springframework.security.core.userdetails.User.withUsername("ashrf")
                        .password("1234")
                        .authorities("READ")
                        .build();
                return user;
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
