package eg.gov.iti.jets.service.aop;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class InstanceLoggingAspect {

    @Autowired
    InstanceLogsDao instanceLogsDao;
    @Autowired
    UserDao userDao;
    @Autowired
    InstanceDao instanceDao;


    @Before("execution(* eg.gov.iti.jets.service.management.InstanceManagement.createInstance(eg.gov.iti.jets.persistence.entity.aws.Instance))")
    public void beforeCreateInstance(JoinPoint joinPoint) throws Throwable {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var authentication = securityContext.getAuthentication();
        System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
        String userName = ((UserDetails) authentication.getPrincipal()).getUsername();
        String password = ((UserDetails) authentication.getPrincipal()).getPassword();
        System.out.println((joinPoint.getArgs()[0]).toString());

        Instance instance = (Instance) joinPoint.getArgs()[0];
        User user = userDao.findByUsernameAndPassword(userName,password).orElse(null);

        InstanceLogs instanceLogs = new InstanceLogs();
        instanceLogs.setInstance(instance);
        instanceLogs.setDateTime(LocalDateTime.now());
        instanceLogs.setActionMaker(user);
        instanceLogs.setAction(UserAction.CREATE_INSTANCE);
        System.out.println(instanceLogs.toString());

//        instanceLogsDao.save(instanceLogs);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
    }


    @Before("execution(* eg.gov.iti.jets.service.management.InstanceManagement.startInstance(..))")
    public void beforeStartInstance(JoinPoint joinPoint) throws Throwable {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var authentication = securityContext.getAuthentication();
        System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
    }


    @Before("execution(* eg.gov.iti.jets.service.management.InstanceManagement.stopInstance(..))")
    public void beforeStopInstance(JoinPoint joinPoint) throws Throwable {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var authentication = securityContext.getAuthentication();
        System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
    }


    @Before("execution(* eg.gov.iti.jets.service.management.InstanceManagement.deleteInstance(..))")
    public void beforeDeletenstance(JoinPoint joinPoint) throws Throwable {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var authentication = securityContext.getAuthentication();
        System.out.println(((UserDetails) authentication.getPrincipal()).getUsername());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
    }
}