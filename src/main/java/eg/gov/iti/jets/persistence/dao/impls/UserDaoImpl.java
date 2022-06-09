package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.Track;
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
        if (entity == null || entity.getId() == null) {
            throw new NullPointerException("entity or id can't be null");
        }
        return userRepo.save(entity);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepo.findById(integer);
    }

    @Override
    public <C> Optional<C> findById(Integer id, Class<C> projection) {
        return userRepo.findById(id, projection);
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
        Page<C> page = userRepo.findBy(PageRequest.of(pageNumber, pageSize),projection);
        return page.getContent();
    }

    @Override
    public List<User> findAllByExample(User example) {
        return userRepo.findAll(Example.of(example));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return userRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher),projection);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String userName, String password) {
        return userRepo.findByUsernameAndPassword(userName, password);
    }


    @Override
    public List<User> findAllUsersByTrackAndRole(Track track, Role role) {
        return userRepo.findAllByTracksEqualsAndRoleEquals(track,role);
    }

    @Override
    public List<User> findAllUsersByTrack(Track track) {
        return userRepo.findAllByTracksEquals(track);
    }

    @Override
    public List<User> findAllUsersByRole(Role role) {
        return userRepo.findAllByRoleEquals(role);
    }

    @Override
    public List<User> findAllFollowers(User user) {
        return userRepo.findAllByManager(user);
    }

    @Override
    public List<User> getUserByBranchIdAndRoleName(int branchId, String roleName) {
        return userRepo.getUserByBranchIdAndRoleName(branchId,roleName);
    }

    @Override
    public List<User> getUserByTrackIdAndRoleName(int trackId, String roleName) {
        return userRepo.getUserByTrackIdAndRoleName(trackId,roleName);
    }

    @Override
    public List<User> getUserByIntakeIdAndRoleName(int intakeId, String roleName) {
        return userRepo.getUserByIntakeIdAndRoleName(intakeId,roleName);
    }

    @Override
    public List<User> getUserByTrainingIdAndRoleName(int trainingProgramId, String roleName) {
        return userRepo.getUserByTrainingIdAndRoleName(trainingProgramId,roleName);
    }

    @Override
    public Optional<User> getBranchManger(int branchId) {
        return userRepo.getBranchManger(branchId);
    }


}
