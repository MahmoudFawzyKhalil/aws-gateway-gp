package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.util.Mapper;
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
    private Mapper mapper;
    public UserManagementImpl(UserDaoImpl userDao , Mapper mapper){
        this.userDaoImpl = userDao;
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
    public UserResponse getUserById(int id ) {

        return mapper.mapFromUserToUserResponse(userDaoImpl.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id : "+id+" , Not found")));
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public Boolean createUserFromCSV( String csvFile ) {
        return null;
    }

}
