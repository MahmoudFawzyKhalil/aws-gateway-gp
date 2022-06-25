package eg.gov.iti.jets.service.management.impl;


import eg.gov.iti.jets.api.config.CustomUserDetailsManager;
import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public String authenticate(String username, String password){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }catch (AuthenticationException e){
            throw new ResourceNotFoundException("Incorrect username or password", e);
        }
        UserAdapter userDetails = customUserDetailsManager.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public User getUserInfo( Integer userId ) {
        Optional<User> user = userDao.findById( userId );
        return user.orElseThrow(()-> new ResourceNotFoundException( "This user isn't exist" ));
    }

}
