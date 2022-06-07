package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericCrudDao<User, Integer> {

    Optional<User> findByUsernameAndPassword(String userName, String password);


    //Add the track and role to return users in that track with the role provided
    List<User> findAllUsersByTrackAndRole(Track track, Role role);

    //Get All Users by track
    List<User> findAllUsersByTrack(Track track);

    //Get All Users by Role
    List<User> findAllUsersByRole(Role role);

    //Get All students if instructor attached to them
    //Get All instructors if Supervisor Attached to them ....
    //Add the User you want to get all followers bellow it
    List<User> findAllFollowers(User user);

}
