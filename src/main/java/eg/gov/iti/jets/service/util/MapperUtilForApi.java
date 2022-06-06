package eg.gov.iti.jets.service.util;

import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MapperUtilForApi {
    @Autowired
    private SecurityGroupDao securityGroupDao;
    @Autowired
    UserDao userDao;
    @Autowired
    AwsGateway awsGateway;

    public List<SecurityGroup> getSecurityGroups(List<String> ids){
        List<SecurityGroup> securityGroups = new ArrayList<>();
        for (String id:ids){
            SecurityGroup securityGroup = new SecurityGroup();
            securityGroup.setSecurityGroupId( id );
            System.out.println(securityGroupDao.findAllByExample( securityGroup ));
            SecurityGroup securityGroup1 = securityGroupDao.findAllByExample( securityGroup ).get( 0 );
            securityGroups.add( securityGroup1 );

        }
        return securityGroups;
    }

    public List<String> getSecurityGroupsName(List<SecurityGroup> securityGroups){
        List<String> securityGroupsNames = new ArrayList<>();
        for (SecurityGroup securityGroup:securityGroups){
            securityGroupsNames.add( securityGroup.getName() );

        }
        return securityGroupsNames;
    }

    public User getUser( int id){
        Optional<User> byId = userDao.findById( id );
        return byId.orElse( null );
    }


    public Ami getAmiObject( String amiId){
        Optional<Ami> ami = awsGateway.describeAmi( amiId );
        return ami.orElse( null );
    }
}
