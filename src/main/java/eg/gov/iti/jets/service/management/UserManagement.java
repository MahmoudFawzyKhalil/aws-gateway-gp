package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserManagement extends UserDetailsService{

    UserDetails loadUserByUsername( String username) throws UsernameNotFoundException;
    Boolean createUser( User user );
    User updateUser( User user );
    Boolean deleteUser( int id );
    List<User> getAllUser();
     UserResponse getUserById(int id );

    Boolean createUserFromCSV( String csvFile );

    User getUserByName(String username);
}
