package eg.gov.iti.jets.service.management;


import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface StudentManagement extends UserDetailsService {

    public List<User> getAllStudent();
}
