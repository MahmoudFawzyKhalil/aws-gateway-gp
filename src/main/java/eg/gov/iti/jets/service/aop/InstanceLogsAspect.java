package eg.gov.iti.jets.service.aop;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.InstanceLogs;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class InstanceLogsAspect {

    private final InstanceLogsDao instanceLogsDao;
    private final UserDao userDao;

    private final InstanceDao instanceDao;

    public InstanceLogsAspect(InstanceLogsDao instanceLogsDao, UserDao userDao, InstanceDao instanceDao) {
        this.instanceLogsDao = instanceLogsDao;
        this.userDao = userDao;
        this.instanceDao = instanceDao;
    }


//    @AfterReturning("execution(* eg.gov.iti.jets.service.management.InstanceManagement.createInstance(eg.gov.iti.jets.persistence.entity.aws.Instance)), returning = instance")

    @AfterReturning(
            pointcut = "execution(* eg.gov.iti.jets.service.management.InstanceManagement.createInstance(eg.gov.iti.jets.persistence.entity.aws.Instance))",
            returning = "returnedInstance")
    public void afterCreateInstance(JoinPoint joinPoint, Object returnedInstance) throws Throwable {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        User user = userDao.findByUsername(userName).orElse(null);
        Instance instance = (Instance) returnedInstance;
        InstanceLogs instanceLogs = new InstanceLogs();
        instanceLogs.setInstance(instance);
        instanceLogs.setDateTime(LocalDateTime.now());
        instanceLogs.setActionMaker(user);
        instanceLogs.setAction(UserAction.CREATE_INSTANCE);
        System.out.println(instanceLogs.toString());

        instanceLogsDao.save(instanceLogs);

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