package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepo userRepo;

    public UserDaoImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepo.findById(integer);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<User> findAllByExample(User example) {
        return null;
    }


    @Override
    public Optional<User> findByUserNameAndPassword(String userName, String password) {
        return userRepo.findByUserNameAndPassword(userName, password);
    }
}
