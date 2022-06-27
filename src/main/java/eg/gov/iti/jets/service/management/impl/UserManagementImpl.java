package eg.gov.iti.jets.service.management.impl;


import eg.gov.iti.jets.api.config.CustomUserDetailsManager;
import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagementImpl implements UserManagement {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    private final UserDao userDao;



    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        return users;
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + " , not found"));
    }

    @Override
    public String authenticate(String username, String password){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }catch ( AuthenticationException e){
            throw new ResourceNotFoundException("Incorrect username or password", e);
        }
        UserAdapter userDetails = customUserDetailsManager.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public Boolean updateUserPassword(String oldPassword , String newPassword , int id) {
        Optional<User> user = userDao.findById( id );
        if(user.isPresent()){
            if(user.get().getPassword().equals( oldPassword )){
                user.get().setPassword( newPassword );
                User update = userDao.update( user.get() );
                return true;
            }else {
                return false;
            }
        }else {
            throw new ResourceNotFoundException( "This user doesn't exist" );
        }
    }

    @Override
    public User getUserInfo( Integer userId ) {
        Optional<User> user = userDao.findById( userId );
        return user.orElseThrow(()-> new ResourceNotFoundException( "This user isn't exist" ));
    }

}
