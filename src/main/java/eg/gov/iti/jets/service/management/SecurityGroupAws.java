package eg.gov.iti.jets.service.management;

import java.util.List;

public interface SecurityGroupAws {
    List<eg.gov.iti.jets.persistence.entity.aws.SecurityGroup> describeSecurityGroupsForVpc( String vpcId);
}
