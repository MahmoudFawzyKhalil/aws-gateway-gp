package eg.gov.iti.jets.service.aop;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InstanceLoggingAspect {


    @Before("execution(* eg.gov.iti.jets.service.management.InstanceManagement.createInstance(eg.gov.iti.jets.persistence.entity.aws.Instance))")
    public void aroundCreateInstance(JoinPoint joinPoint) throws Throwable {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var authentication = securityContext.getAuthentication();
        System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");

    }
}