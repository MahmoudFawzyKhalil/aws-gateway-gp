package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.api.resource.user.UserRequest;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserManagement extends UserDetailsService{

    UserDetails loadUserByUsername( String username) throws UsernameNotFoundException;
    String createUser( UserRequest userRequest );
    String updateUser( UserRequest userRequest );
    String deleteUser( int id );
    List<UserResponse> getAllUsers();
     UserResponse getUserById(int id );

    Boolean createUserFromCSV( String csvFile );

    User getUserByName(String username);
}
