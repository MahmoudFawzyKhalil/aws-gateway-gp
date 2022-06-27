package eg.gov.iti.jets.service.util.model;

import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserAdapter implements UserDetails , Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final User user;

    private String jwt;



    private Set<GrantedAuthority> authorities; // todo let it be final

    public UserAdapter(User user , Collection<? extends GrantedAuthority> authorities ){

        this.user = user;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRole().getPrivileges().stream()
//                .map(Privilege::getName)
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        return authorities;
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
    public Integer getId(){
        return user.getId();
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new Comparator<GrantedAuthority>() {
            @Override
            public int compare(GrantedAuthority o1, GrantedAuthority o2) {
                if (o2.getAuthority() == null) {
                    return -1;
                }
                if (o1.getAuthority() == null) {
                    return 1;
                }
                return o1.getAuthority().compareTo(o2.getAuthority());
            }
        });
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
