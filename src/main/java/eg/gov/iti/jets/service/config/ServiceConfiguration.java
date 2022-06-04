package eg.gov.iti.jets.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ec2.Ec2Client;

@Configuration
public class ServiceConfiguration {
    @Bean
    public Ec2Client getEc2Client(){
        return Ec2Client.create();
    }
}
