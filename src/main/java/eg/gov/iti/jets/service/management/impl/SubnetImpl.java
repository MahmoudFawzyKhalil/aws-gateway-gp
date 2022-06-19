package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.SubnetAws;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubnetImpl implements SubnetAws {
    final
    AwsGateway awsGateway;

    public SubnetImpl( AwsGateway awsGateway ) {
        this.awsGateway = awsGateway;
    }

    @Override
    public List<Subnet> getAllSubnets(){
        return awsGateway.describeAllSubnets();
    }
}
