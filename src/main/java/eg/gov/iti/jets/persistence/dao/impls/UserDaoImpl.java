package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepo userRepo;

    public UserDaoImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDao save(UserDao entity) {
        return null;
    }

    @Override
    public UserDao update(UserDao entity) {
        return null;
    }

    @Override
    public Optional<UserDao> findById(Integer integer) {
        return Optional.empty();
    }
}
