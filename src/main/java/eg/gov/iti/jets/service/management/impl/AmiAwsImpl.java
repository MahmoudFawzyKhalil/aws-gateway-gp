package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.AmiAws;
import org.springframework.stereotype.Service;

@Service
public class AmiAwsImpl implements AmiAws {
    private final AwsGateway awsGateway;

    public AmiAwsImpl( AwsGateway awsGateway ) {
        this.awsGateway = awsGateway;
    }

    @Override
    public Ami describeAmi(String amiId){
        return awsGateway.describeAmi(amiId).orElseThrow(()->new ResourceNotFoundException("Ami with id" + amiId + ", is not found"));
    }
}
