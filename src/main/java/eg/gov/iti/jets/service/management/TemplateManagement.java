package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;

import java.util.List;

public interface TemplateManagement {
    List<TemplateConfiguration> getTemplateConfiguration();
    Boolean createTemplate( List<String> configs);
    Boolean deleteTemplate ( int id );

}
