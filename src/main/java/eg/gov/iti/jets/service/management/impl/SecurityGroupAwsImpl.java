package eg.gov.iti.jets.service.management.impl;



import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.SecurityGroupAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityGroupAwsImpl implements SecurityGroupAws {
    @Autowired
    AwsGateway awsGateway;
    @Autowired
    SecurityGroupDao securityGroupDao;
    @Override
    public List<SecurityGroup> describeSecurityGroupsForVpc( String vpcId){
        return awsGateway.describeSecurityGroupsForVpc(vpcId);
    }

    @Override
    public SecurityGroup createSecurityGroup(SecurityGroup securityGroup) {
        return securityGroupDao.save( securityGroup );
    }


}
