package eg.gov.iti.jets.service.config;

import eg.gov.iti.jets.service.aop.StartingTtlCountDownScheduler;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

@Configuration
public class ServiceConfiguration {
    @Bean
    public Ec2Client getEc2Client() {
        Ec2Client ec2Client = Ec2Client.builder().region( Region.US_EAST_1 ).credentialsProvider( ProfileCredentialsProvider.create() )
                .build();
        return ec2Client;
    }

    @Bean
    public StartingTtlCountDownScheduler startingTtlCountDownSchedulerAdvice() {
        return new StartingTtlCountDownScheduler();
    }

//    @Bean
//    public ProxyFactoryBean timerProxy() {
//        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTargetName( "awsGatewayImpl" );
//        proxyFactoryBean.setInterceptorNames( "startingTtlCountDownScheduler" );
//        return proxyFactoryBean;
//    }
    @Bean
    public NameMatchMethodPointcut pointcutByName(){
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames( "createInstance" , "startInstance" );
        return nameMatchMethodPointcut;
    }
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut( pointcutByName() );
        defaultPointcutAdvisor.setAdvice( startingTtlCountDownSchedulerAdvice() );
        return defaultPointcutAdvisor;
    }
    @Bean
        public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(8);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

}
