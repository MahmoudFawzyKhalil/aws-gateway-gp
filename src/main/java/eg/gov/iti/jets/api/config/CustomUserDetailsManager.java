package eg.gov.iti.jets.api.config;

import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsManager implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Transactional //(readOnly = true)
    @Override
    public eg.gov.iti.jets.service.model.UserAdapter loadUserByUsername(String username) throws UsernameNotFoundException {

        eg.gov.iti.jets.persistence.entity.User user = new eg.gov.iti.jets.persistence.entity.User();
        user.setUsername(username);
        List<User> resultList = userDao.findAllByExample(user);
        user = resultList == null ? null : resultList.get(0);

        return user == null ? null : new eg.gov.iti.jets.service.model.UserAdapter(user, user.getRole()
                .getPrivileges()
                .stream().map(privilege -> privilege.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

}
