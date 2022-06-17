package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "/api/templates" )
public class TemplateController {

    final TemplateManagement templateManagement;
    final Mapper mapper;

    public TemplateController( TemplateManagement templateManagement, Mapper mapper ) {
        this.templateManagement = templateManagement;
        this.mapper = mapper;
    }

    @PostMapping
    @Secured( "MANAGE_TEMPLATE" )
    public ResponseEntity<?> createTemplate( @RequestBody TemplateRequest templateRequest, @AuthenticationPrincipal UserAdapter userDetails ) {
        Integer creatorId = userDetails.getId();
        Boolean template = templateManagement.createTemplate( mapper.mapFromTemplateRequestToTemplateConfig( templateRequest, creatorId ) );
        if ( template ) {
            return new ResponseEntity<>( true, HttpStatus.CREATED );
        } else {
            return new ResponseEntity<>( false, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping
//    @Secured( "VIEW_TEMPLATES" )
    public ResponseEntity<?> getAllTemplates( @AuthenticationPrincipal UserAdapter userDetails ) {
        List<TemplateResponse> templateResponses = new ArrayList<>();
        List<TemplateResponse> templateConfiguration = templateManagement.getTemplateConfigurationById( userDetails.getId() );
//        for ( TemplateConfiguration template :
//                templateConfiguration ) {
//            TemplateResponse templateResponse = mapper.mapFromTemplateToTemplateResponse( template );
//            templateResponses.add( templateResponse );
//        }
        TemplateViewResponse templateViewResponse = new TemplateViewResponse( templateConfiguration );
        return new ResponseEntity<>( templateViewResponse, HttpStatus.OK );
    }

//    @DeleteMapping("/{id}")
//    @Secured("MANAGE_TEMPLATE")
//    public SuccessResponse deleteTemplate ( @PathVariable int id ){
//        return new SuccessResponse(templateManagement.deleteTemplate( id )) ;
//    }

}
