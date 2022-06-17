package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.AmiAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmiAwsImpl implements AmiAws {
    @Autowired
    AwsGateway awsGateway;

    @Override
    public Optional<Ami> describeAmi( String amiId){
        return awsGateway.describeAmi(amiId);
    }
}
