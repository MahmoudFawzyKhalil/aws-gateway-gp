package eg.gov.iti.jets.service.management.impl;


import eg.gov.iti.jets.api.config.CustomUserDetailsManager;
import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

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
        }catch (BadCredentialsException e){
            throw new RuntimeException("Incorrect username or password", e);
        }
        UserAdapter userDetails = customUserDetailsManager.loadUserByUsername(username);
        System.out.println( userDetails.getAuthorities() );
        return jwtUtil.generateToken(userDetails);
    }

}
