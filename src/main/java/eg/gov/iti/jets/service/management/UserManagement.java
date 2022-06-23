package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;

import java.util.List;

public interface UserManagement  {


    User createUser(User user);

    User updateUser(User user);

    String deleteUser(int id);

    List<User> getAllUsers();

    User getUserById(int id);

    List<User> getAllStudentUsers();

    Boolean createUserFromCSV(String csvFile);

    User getUserByName(String username);

    List<User> getTrackStudents(Track track);

    String authenticate(String username, String password);

//    List<User> getSupervisorInstructors(User user);
}