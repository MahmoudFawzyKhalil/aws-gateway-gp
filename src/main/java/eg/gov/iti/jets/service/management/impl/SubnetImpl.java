package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.SubnetAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubnetImpl implements SubnetAws {
    @Autowired
    AwsGateway awsGateway;
    @Override
    public List<Subnet> getAllSubnets(){
        return awsGateway.describeAllSubnets();
    }
}
