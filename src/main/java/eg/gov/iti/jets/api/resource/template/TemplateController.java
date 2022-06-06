package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.resource.subnet.SubnetObjectResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    final TemplateManagement templateManagement;
    final Mapper mapper;

    public TemplateController( TemplateManagement templateManagement, Mapper mapper ) {
        this.templateManagement = templateManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public SuccessResponse createTemplate(@RequestBody TemplateRequest templateRequest ){
       return new SuccessResponse(templateManagement.createTemplate(mapper.mapFromTemplateRequestToTemplateConfig(templateRequest)));
    }

    @DeleteMapping("/{id}")
    public SuccessResponse deleteTemplate ( @PathVariable int id ){
        return new SuccessResponse(templateManagement.deleteTemplate( id )) ;
    }


    @GetMapping
    // TODO: 6/5/2022 get the Id?
    public TemplateViewResponse getAllTemplates(){
        List<TemplateResponse> templateResponses = new ArrayList<>();
        List<TemplateConfiguration> templateConfiguration = templateManagement.getTemplateConfiguration();
        for ( TemplateConfiguration template :
                templateConfiguration ) {
            TemplateResponse templateResponse = mapper.mapFromTemplateToTemplateResponse( template );
            templateResponses.add( templateResponse );
        }

        return new TemplateViewResponse(templateResponses);
    }



}
