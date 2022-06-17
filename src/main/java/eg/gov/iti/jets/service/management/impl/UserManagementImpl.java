package eg.gov.iti.jets.service.management.impl;


import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagementImpl implements UserDetailsService, UserManagement {
    private final UserDao userDao;

    @Transactional
    @Override
    public UserAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * todo
         * first talk with repo to load user
         * user userAdapter to convert user entity to user details
         */
        User user = new User();
        user.setUsername(username);
        List<User> resultList = userDao.findAllByExample(user);
        user = resultList == null ? null : resultList.get(0);

//        switch (username){
//            case "hesham":
//                User user = new User();
//                user.setId(1);
//                user.setUsername("hesham");
//                user.setPassword("1234");
////                Privilege privilege = new Privilege();
////                privilege.setName("WRITE");
////                Role role = new Role();
////                role.setPrivileges(List.of(privilege));
////                user.setRole(role);
//                return new UserAdapter(user, AuthorityUtils.createAuthorityList("WRITE"));
//            default:
//                User user1 = new User();
//                user1.setId(2);
//                user1.setUsername("ashrf");
//                user1.setPassword("1234");
////                Privilege privilege1 = new Privilege();
////                privilege1.setName("READ");
////                Role role1 = new Role();
////                role1.setPrivileges(List.of(privilege1));
////                user1.setRole(role1);
//                return new UserAdapter(user1,AuthorityUtils.createAuthorityList("READ"));
//        }
        return user == null ? null : new UserAdapter(user, user.getRole()
                .getPrivileges()
                .stream().map(privilege -> privilege.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

    @Override
    public User createUser(User user ) {
         return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        return users;
    }

    /**
     * todo
     * get by role from db
     */
    @Override
    public List<User> getAllStudentUsers() {
        List<User> users = userDao.findAll();
        List<User> students = new ArrayList<>();
        for(User user : users ){
            if(user.getRole().getName().equals("ROLE_STUDENT")){
                students.add(user);
            }
        }
        return students;
    }

    @Override
    public User getUserById(int id ) {
        return userDao.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public List<User> getTrackStudents(Track track) {
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_STUDENT");
        return userDao.findAllUsersByTrackAndRole(track, role);
    }

    @Override
    public Boolean createUserFromCSV( String csvFile ) {
        return null;
    }

    @Override
    public String deleteUser( int id ) {
        List<User> users = userDao.findAll();
        for(User user : users){
            if(user.getId() == id){
//                 userDaoImpl.de
            }
        }
        return null;
    }

//    public List<User> getSupervisorInstructors(User user) {
//        return userDao.findAllFollowers(user).stream()
//                .filter(user1 -> user1.getRole().getName().equals("ROLE_INSTRUCTOR")
//                        && !user.getId().equals(user1.getId()))
//                .collect(Collectors.toList());
//    }


    /**
     * return students under only instructor & supervisor
     * @param user
     * @return
     */
//    public List<User> getUserStudents(User user) {
//        List<User> students = new ArrayList<>();
//        userDao.findAllFollowers(user).forEach(user1 -> {
//            if(user1.getRole().getName().equals("ROLE_INSTRUCTOR"))
//                students.addAll(getInstructorStudents(user1));
//            else if(user1.getRole().getName().equals("ROLE_STUDENT"))
//                students.add(user1);
//        });
//        return students;
//    }

//    private List<User> getInstructorStudents(User user) {
//        return userDao.findAllFollowers(user).stream()
//                .filter(user1 -> user1.getRole().getName().equals("ROLE_STUDENT")
//                        && !user.getId().equals(user1.getId()))
//                .collect(Collectors.toList());
//    }


}
