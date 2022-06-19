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
import software.amazon.awssdk.services.ec2.Ec2AsyncClient;
import software.amazon.awssdk.services.ec2.Ec2Client;

@Configuration
public class ServiceConfiguration {
    @Bean
    public Ec2Client getEc2Client() {
        return Ec2Client.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.create()).build();

    }

    @Bean
    public Ec2AsyncClient getAsyncEc2Client() {
        return Ec2AsyncClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.create()).build();

    }

    @Bean
    public StartingTtlCountDownScheduler startingTtlCountDownSchedulerAdvice() {
        return new StartingTtlCountDownScheduler();
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(8);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

}
