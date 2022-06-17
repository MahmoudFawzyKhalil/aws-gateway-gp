package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;

import java.util.List;
import java.util.Optional;

public interface TemplateManagement {
    List<TemplateConfiguration> getTemplateConfiguration();
    List<TemplateConfiguration> getTemplateConfigurationById(int id);
    Boolean createTemplate( TemplateConfiguration templateConfiguration);
    Boolean deleteTemplate ( int id );




}
