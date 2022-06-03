package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsernameAndPassword(String userName, String password);
}
