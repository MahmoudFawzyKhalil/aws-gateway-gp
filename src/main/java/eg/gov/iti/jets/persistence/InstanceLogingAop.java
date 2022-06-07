package eg.gov.iti.jets.persistence;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InstanceLogingAop {


    private SecurityContext securityContext;

    public InstanceLogingAop() {
        this.securityContext = SecurityContextHolder.getContext();
    }

    @Before("execution(* createInstance(..))")
    public void Logging() {
        System.out.println("=====================================================");
        var authentication = securityContext.getAuthentication();
        System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
    }
}
