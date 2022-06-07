package eg.gov.iti.jets.api.resource.test;

import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    public Test(AwsGateway awsGateway) {
        this.awsGateway = awsGateway;
    }

    private AwsGateway awsGateway;

    @GetMapping("/test/go")
    public void go(){
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setAmiId("ami-0022f774911c1d690");
        templateConfiguration.setInstanceType("t1.micro");

        awsGateway.createInstance(templateConfiguration,"uuuu",new KeyPair());
    }

}
