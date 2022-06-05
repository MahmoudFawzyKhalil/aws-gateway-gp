package eg.gov.iti.jets.service.model;

import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserAdapter implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getPrivileges().stream()
                .map(Privilege::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        /**
         * todo
         * user entity should provide this property
         */
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        /**
         * todo
         * user entity should provide this property
         */
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        /**
         * todo
         * user entity should provide this property
         */
        return true;
    }

    @Override
    public boolean isEnabled() {
        /**
         * todo
         * user entity should provide this property
         */
        return true;
    }
}
