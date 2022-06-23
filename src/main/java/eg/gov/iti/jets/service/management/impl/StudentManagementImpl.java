package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.StudentManagement;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentManagementImpl implements UserDetailsService, StudentManagement {

    @Autowired
    UserDao userDao;


    @Transactional //(readOnly = true)
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


    public void addStudents(List<User> users){
        for (User user:users) {
            try {
                userDao.save(user);
            }
            catch (Exception e) {
                throw new ResourceExistException("There is duplicate in Student [ "+ user.getUsername());
            }
        }
    }

    @Override
    public List<User> getAllStudent() {
        return userDao.findUsersByRoleName("Student",User.class);
    }
}
