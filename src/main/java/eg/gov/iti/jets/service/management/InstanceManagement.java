package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.*;

import java.util.List;
import java.util.Optional;

public interface InstanceManagement {
    List<SecurityGroup> describeSecurityGroupsForVpc( String vpcId);
    List<String> getInstanceTypes();
    List<Subnet> getAllSubnet();
    Optional<Ami> describeAmi( String amiId);
    Instance createInstance( int templateConfigurationId, String instanceName , String keyPair);
}
