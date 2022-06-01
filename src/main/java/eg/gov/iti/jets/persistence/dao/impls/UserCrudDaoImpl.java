package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.UserCrudDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserCrudDaoImpl implements UserCrudDao {
    private final UserRepo userRepo;

    public UserCrudDaoImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserCrudDao save(UserCrudDao entity) {
        return null;
    }

    @Override
    public UserCrudDao update(UserCrudDao entity) {
        return null;
    }

    @Override
    public Optional<UserCrudDao> findById(Integer integer) {
        return Optional.empty();
    }
}
