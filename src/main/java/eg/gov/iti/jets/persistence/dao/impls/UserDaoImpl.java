package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public <C> Optional<C> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        Page<User> instancePage = userRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return instancePage.toList();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<User> findAllByExample(User example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return userRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        return null;
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String userName, String password) {
        return userRepo.findByUsernameAndPassword(userName, password);
    }


}
