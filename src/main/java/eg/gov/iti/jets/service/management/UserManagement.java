package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserManagement extends UserDetailsService {

    @Override
    UserAdapter loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> getAllUsers();
}