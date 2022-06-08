package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserManagement extends UserDetailsService {

    @Override
    UserAdapter loadUserByUsername(String username) throws UsernameNotFoundException;

    User createUser(User user);

    User updateUser(User user);

    String deleteUser(int id);

    List<User> getAllUsers();

    User getUserById(int id);

    List<User> getAllStudentUsers();

    Boolean createUserFromCSV(String csvFile);

    User getUserByName(String username);

    List<User> getUserStudents(User user);

    List<User> getSupervisorInstructors(User user);
}