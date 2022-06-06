package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.user.UserRequest;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.dao.impls.RoleDaoImpl;
import eg.gov.iti.jets.persistence.dao.impls.UserDaoImpl;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.UserManagement;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementImpl implements UserManagement {
    private UserDaoImpl userDaoImpl;
    private RoleDaoImpl roleDaoImpl;
    private Mapper mapper;
    public UserManagementImpl(UserDaoImpl userDao , RoleDaoImpl roleDaoImpl , Mapper mapper){
        this.userDaoImpl = userDao;
        this.roleDaoImpl =roleDaoImpl;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        switch (username){
            case "hesham":
                var user = org.springframework.security.core.userdetails.User.withUsername("hesham")
                        .password("1234")
                        .authorities("WRITE")
                        .build();
                return user;
            default:
                user = org.springframework.security.core.userdetails.User.withUsername("ashrf")
                        .password("1234")
                        .authorities("READ")
                        .build();
                return user;
        }
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

    @Override
    public User getUserById(int id ) {
        return userDaoImpl.findById(id).orElseThrow(()->new RuntimeException("User with this id not exists"));
    }

    @Override
    public User getUserByName(String username) {
        return null;
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
                // userDaoImpl.d
            }
        }
        return null;
    }

}
