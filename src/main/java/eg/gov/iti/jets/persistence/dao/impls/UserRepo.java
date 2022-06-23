package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String userName, String password);

    Optional<User> findByUsername(String userName);

    <C> Optional<C> findById(Integer id, Class<C> projection);

    <C> Page<C> findBy(PageRequest pageRequest, Class<C> projection);

    <C> List<C> findAllBy(Example<C> example, Class<C> projection);

    List<User> findAllByTracksEquals(Track track);

    List<User> findAllByTracksEqualsAndRoleEquals(Track track, Role role);

    List<User> findAllByRoleEquals(Role role);

    List<User> findAllByManager(User user);


    @Query("select distinct u from Branch b join b.trainingPrograms tr join tr.intakes i join i.tracks t join t.users u where u.role.name=:roleName and b.id=:branchId")
    List<User> getUserByBranchIdAndRoleName(int branchId, String roleName);

    @Query("select distinct u from TrainingProgram tr join tr.intakes i join i.tracks t join t.users u where u.role.name=:roleName and tr.id=:trainingProgramId")
    List<User> getUserByTrainingIdAndRoleName(int trainingProgramId, String roleName);

    @Query("select distinct u from Intake i join i.tracks t join t.users u where u.role.name=:roleName and i.id=:intakeId")
    List<User> getUserByIntakeIdAndRoleName(int intakeId, String roleName);

    @Query("select distinct u from Track t join t.users u where u.role.name=:roleName and t.id=:trackId")
    List<User> getUserByTrackIdAndRoleName(int trackId, String roleName);

    @Query("select u from Branch b join b.trainingManager u where b.id=:branchId")
    Optional<User> getBranchManger(int branchId);

    <C> List<C> findAllByTracks_idAndRole_NameLike(int trackId, String roleName, Class<C> projection);

    <C> List<C> findUserByRole_Name(String rollName, Class<C> projection);

    Optional<User> findUserByIdAndRole_Name(Integer id, String rollName);


}
