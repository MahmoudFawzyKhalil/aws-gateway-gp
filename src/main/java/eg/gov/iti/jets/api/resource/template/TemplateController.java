package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> createTemplate(@RequestBody TemplateRequest templateRequest ){
       Boolean successResponse = templateManagement.createTemplate(mapper.mapFromTemplateRequestToTemplateConfig(templateRequest));
       if(successResponse==true)
       return new ResponseEntity<>(successResponse, HttpStatus.OK);
       else {
           return new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT);
       }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTemplate ( @PathVariable int id ){
        return templateManagement.deleteTemplate( id );
    }


    @GetMapping
    // TODO: 6/5/2022 get the Id?
    public List<TemplateResponse> getAllTemplates(){
        List<TemplateResponse> templateResponses = new ArrayList<>();
        List<TemplateConfiguration> templateConfiguration = templateManagement.getTemplateConfiguration();
        for ( TemplateConfiguration template :
                templateConfiguration ) {
            TemplateResponse templateResponse = mapper.mapFromTemplateToTemplateResponse( template );
            templateResponses.add( templateResponse );
        }
        return templateResponses;
    }

    // TODO: 6/2/2022 3ayzen bs two methods mkmlesh wala 3ala hasab mariam
}
