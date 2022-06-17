package eg.gov.iti.jets.service.aop;


import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.gateway.aws.ec2.scheduler.RunningTasks;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;


public class StartingTtlCountDownScheduler implements MethodInterceptor {
    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    AwsGateway timerProxy;

    @Override
    public Object invoke( MethodInvocation invocation ) throws Throwable {
        System.out.println( "************************before proceed " );
        Object[] arguments = invocation.getArguments();
        int timeToLiveInMinutes = (int)arguments[3];
        Object result = invocation.proceed();
        Instance instance = (Instance) result;
        String instanceId = instance.getInstanceId();
        Date date = new Date( System.currentTimeMillis() + timeToLiveInMinutes * 60_000 );
        ScheduledFuture future = threadPoolTaskScheduler.schedule( ()-> timerProxy.stopInstance( instanceId ), date );
        RunningTasks.INSTANCE.addTask( instanceId, future );

        System.out.println( "*********************after proceed" );

        return result;
    }
}

//@Aspect
//public class StartingTtlCountDownScheduler {
//
//    @Around( "createInstance()" )
//    public Object around( ProceedingJoinPoint invocation ) throws Throwable {
//
//        return null;
//    }
//
//}
