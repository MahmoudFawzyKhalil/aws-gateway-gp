package eg.gov.iti.jets.service.management.impl;

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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementImpl implements UserDetailsService, UserManagement {

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
        return user == null ? null : new UserAdapter(user , user.getRole()
                                                                .getPrivileges()
                                                                .stream().map(Privilege::getName)
                                                                .map(SimpleGrantedAuthority::new)
                                                                .collect(Collectors.toList()));
    }

    @Override
    public Boolean createUser( User user ) {
        return null;
    }

    @Override
    public User updateUser( User user ) {
        return null;
    }

    @Override
    public Boolean deleteUser( int id ) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserById( int id ) {
        return null;
    }

    @Override
    public Boolean createUserFromCSV( String csvFile ) {
        return null;
    }
}
