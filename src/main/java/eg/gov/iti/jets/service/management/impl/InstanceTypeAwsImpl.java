package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.InstanceTypeAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstanceTypeAwsImpl implements InstanceTypeAws {
    @Autowired
    AwsGateway awsGateway;

    @Override
    public List<String> getInstanceTypes(){
        return awsGateway.getInstanceTypes();
    }
}
