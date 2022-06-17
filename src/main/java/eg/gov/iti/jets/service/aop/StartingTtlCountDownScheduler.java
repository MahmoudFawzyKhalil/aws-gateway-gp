package eg.gov.iti.jets.service.aop;


import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.gateway.aws.ec2.scheduler.RunningTasks;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;


@Aspect
public class StartingTtlCountDownScheduler {
    @Autowired
    AwsGateway timerProxy;

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Around( "execution(* eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway.createInstance(..))" )
    public Object aroundCreateInstance( ProceedingJoinPoint invocation ) throws Throwable {
        Object[] arguments = invocation.getArgs();
        Long timeToLiveInMinutes = (Long) arguments[3];
        Object result = invocation.proceed();
        Instance instance = (Instance) result;
        String instanceId = instance.getInstanceId();
        Date date = new Date( System.currentTimeMillis() + timeToLiveInMinutes * 60_000 );
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule( () ->
            timerProxy.stopInstance( instanceId ) , date );

        RunningTasks.INSTANCE.addTask( instanceId, future );

        return result;
    }

    @Around( "execution(* eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway.startInstance(..))" )
    public Object aroundStartInstance( ProceedingJoinPoint invocation ) throws Throwable {
        Object[] arguments = invocation.getArgs();
        Instance instance = (Instance) arguments[0];

        Object result = invocation.proceed();

        Date date = new Date( System.currentTimeMillis() + instance.getTimeToLiveInMinutes() * 60_000 );
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule( () -> {
            timerProxy.stopInstance( instance.getInstanceId() );
            RunningTasks.INSTANCE.removeTask( instance.getInstanceId() );
        }, date );
        RunningTasks.INSTANCE.addTask( instance.getInstanceId(), future );

        return result;
    }

    @Around( "execution(* eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway.stopInstance(..))" )
    public Object aroundStopInstance( ProceedingJoinPoint invocation ) throws Throwable {
        String instanceId = (String) invocation.getArgs()[0];
        RunningTasks.INSTANCE.removeAndCancelTask( instanceId );
        return invocation.proceed();
    }

    @Around( "execution(* eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway.terminateInstance(..))" )
    public Object aroundTerminateInstance( ProceedingJoinPoint invocation ) throws Throwable {
        String instanceId = (String) invocation.getArgs()[0];
        RunningTasks.INSTANCE.removeAndCancelTask( instanceId );
        return invocation.proceed();
    }


}
