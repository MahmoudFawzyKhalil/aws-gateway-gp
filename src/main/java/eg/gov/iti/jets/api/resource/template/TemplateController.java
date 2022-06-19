package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @Secured("MANAGE_TEMPLATE")
    public SuccessResponse createTemplate(@RequestBody TemplateRequest templateRequest , @AuthenticationPrincipal UserAdapter userDetails ){
        Integer id = userDetails.getId();
        return new SuccessResponse(templateManagement.createTemplate(mapper.mapFromTemplateRequestToTemplateConfig(templateRequest , id)));
    }

    @DeleteMapping("/{id}")
    @Secured("MANAGE_TEMPLATE")
    public SuccessResponse deleteTemplate ( @PathVariable int id ){
        return new SuccessResponse(templateManagement.deleteTemplate( id )) ;
    }


    @GetMapping
//    @Secured("VIEW_TEMPLATES")
    // TODO: 6/5/2022 get the Id
    //  ?
    public TemplateViewResponse getAllTemplates(@AuthenticationPrincipal UserAdapter userDetails){
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
