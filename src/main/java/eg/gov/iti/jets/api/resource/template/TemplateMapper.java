package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateMapper {
    // template Mapping
    private final
    MapperUtilForApi mapperUtilForApi;

    public TemplateMapper( MapperUtilForApi mapperUtilForApi ) {
        this.mapperUtilForApi = mapperUtilForApi;
    }

    public TemplateConfiguration mapFromTemplateRequestToTemplateConfig( TemplateRequest templateRequest , int id) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setAmiId( templateRequest.getAmiId() );
        templateConfiguration.setCreator( mapperUtilForApi.getUser( id ) );
        templateConfiguration.setSubnetId( templateRequest.getSubnetId() );
        templateConfiguration.setInstanceType( templateRequest.getInstanceType() );
        templateConfiguration.setSecurityGroups( mapperUtilForApi.getSecurityGroups( templateRequest.getSecurityGroups() ) );
        templateConfiguration.setInstructors( mapperUtilForApi.getUsers(templateRequest.getInstructorIds()) );
        return templateConfiguration;
    }
}
