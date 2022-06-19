package eg.gov.iti.jets.service.management.impl;



import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.SecurityGroupAws;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityGroupAwsImpl implements SecurityGroupAws {
    final
    AwsGateway awsGateway;
    final
    SecurityGroupDao securityGroupDao;

    public SecurityGroupAwsImpl( AwsGateway awsGateway, SecurityGroupDao securityGroupDao ) {
        this.awsGateway = awsGateway;
        this.securityGroupDao = securityGroupDao;
    }

    @Override
    public List<SecurityGroup> describeSecurityGroupsForVpc( String vpcId){
        return awsGateway.describeSecurityGroupsForVpc(vpcId);
    }

    @Override
    public SecurityGroup createSecurityGroup(SecurityGroup securityGroup) {
        try {
            return securityGroupDao.save( securityGroup );
        }catch (Exception e) {
            throw new ResourceExistException("Security group with id or name, is already exist!");
        }
    }


}
