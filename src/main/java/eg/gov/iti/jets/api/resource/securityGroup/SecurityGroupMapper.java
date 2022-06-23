package eg.gov.iti.jets.api.resource.securityGroup;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import org.springframework.stereotype.Service;

@Service
public class SecurityGroupMapper {
    // SecurityGroup Mapping
    public SecurityGroupResponse mapFromSecurityGroupToSecurityGroupResponse( SecurityGroup securityGroup ) {
        SecurityGroupResponse securityGroupResponse = new SecurityGroupResponse();
        securityGroupResponse.setId( securityGroup.getId() );
        securityGroupResponse.setName( securityGroup.getName() );
        securityGroupResponse.setSecurityGroupId( securityGroup.getSecurityGroupId() );
        securityGroupResponse.setDescription( securityGroup.getDescription() );
        securityGroupResponse.setVpcId( securityGroup.getVpcId() );
        return securityGroupResponse;
    }
}
