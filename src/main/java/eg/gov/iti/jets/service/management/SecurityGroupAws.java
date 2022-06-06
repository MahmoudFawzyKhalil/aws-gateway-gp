package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;

import java.util.List;

public interface SecurityGroupAws {
    List<eg.gov.iti.jets.persistence.entity.aws.SecurityGroup> describeSecurityGroupsForVpc( String vpcId);
    SecurityGroup createSecurityGroup(SecurityGroup securityGroup);
}
