package eg.gov.iti.jets.service.management;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        switch (username){
            case "hesham":
                var user = User.withUsername("hesham")
                        .password("1234")
                        .authorities("WRITE")
                        .build();
                return user;
            default:
                user = User.withUsername("ashrf")
                        .password("1234")
                        .authorities("READ")
                        .build();
                return user;
        }
    }
}
