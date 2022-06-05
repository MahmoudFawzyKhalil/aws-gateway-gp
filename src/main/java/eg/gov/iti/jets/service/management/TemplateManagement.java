package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;

import java.util.List;

public interface TemplateManagement {
    List<TemplateConfiguration> getTemplateConfiguration();
    Boolean createTemplate( TemplateConfiguration templateConfiguration);
    Boolean deleteTemplate ( int id );
    List<SecurityGroup> describeSecurityGroupsForVpc(String vpcId);
    List<Subnet> getAllSubnet();
}
