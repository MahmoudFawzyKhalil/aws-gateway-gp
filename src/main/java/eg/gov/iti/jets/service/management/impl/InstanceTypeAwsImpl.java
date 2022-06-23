package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.InstanceTypeAws;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstanceTypeAwsImpl implements InstanceTypeAws {
    final
    AwsGateway awsGateway;

    public InstanceTypeAwsImpl( AwsGateway awsGateway ) {
        this.awsGateway = awsGateway;
    }

    @Override
    public List<String> getInstanceTypes(){
        return awsGateway.getInstanceTypes();
    }
}
