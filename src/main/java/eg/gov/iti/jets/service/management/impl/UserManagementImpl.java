package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.user.UserRequest;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.dao.RoleDao;
import eg.gov.iti.jets.persistence.dao.impls.RoleDaoImpl;
import eg.gov.iti.jets.persistence.dao.impls.UserDaoImpl;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.UserManagement;
//import org.springframework.security.core.userdetails.User;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementImpl implements UserDetailsService, UserManagement {
    @Autowired
    private UserDao userDaoImpl;
    @Autowired
    private RoleDao roleDaoImpl;
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserDao userDao;

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
    public User createUser( User user ) {
         roleDaoImpl.save(user.getRole());
         userDaoImpl.save(user);
         return user;
    }

    @Override
    public User updateUser(User user) {
        roleDaoImpl.update(user.getRole());
        userDaoImpl.update(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDaoImpl.findAll();
        return users;
    }

    /**
     * todo
     * get by role from db
     */
    @Override
    public List<User> getAllStudentUsers() {
        List<User> users = userDaoImpl.findAll();
        List<User> students = new ArrayList<>();
        for(User user : users ){
            if(user.getRole().getName().equals("STUDENT")){
                students.add(user);
            }
        }
        return students;
    }

    @Override
    public User getUserById(int id ) {
        return userDaoImpl.findById(id).orElseThrow(()->new RuntimeException("User with this id not exists"));
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public List<User> getFollowingStudents(User user) {
        return userDao.findAllFollowers(user).stream()
                .filter(user1 -> user1.getRole().getName().equals("STUDENT")
                && !user.getId().equals(user1.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowingInstructors(User user) {
        return userDao.findAllFollowers(user).stream()
                .filter(user1 -> user1.getRole().getName().equals("INSTRUCTOR")
                && !user.getId().equals(user1.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUserFollowers(User user) {
        return userDao.findAllFollowers(user)
                .stream().filter(user1 -> !user.getId().equals(user1.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean createUserFromCSV( String csvFile ) {
        return null;
    }

    @Override
    public String deleteUser( int id ) {
        List<User> users = userDaoImpl.findAll();
        for(User user : users){
            if(user.getId() == id){
//                 userDaoImpl.de
            }
        }
        return null;
    }



}
