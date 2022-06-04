package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserManagement extends UserDetailsService {

    UserDetails loadUserByUsername( String username) throws UsernameNotFoundException;
    Boolean createUser( User user );
    User updateUser( User user );
    Boolean deleteUser( int id );
    List<User> getAllUser();
    User getUserById( int id );
    Boolean createUserFromCSV( String csvFile );

}
