package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.service.management.TemplateManagement;
import eg.gov.iti.jets.service.util.model.UserAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/templates" )
public class TemplateController {

    private final TemplateManagement templateManagement;
    private final TemplateMapper templateMapper;

    public TemplateController( TemplateManagement templateManagement, TemplateMapper templateMapper ) {
        this.templateManagement = templateManagement;
        this.templateMapper = templateMapper;
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TEMPLATE.name())")
    public ResponseEntity<?> createTemplate( @RequestBody TemplateRequest templateRequest, @AuthenticationPrincipal UserAdapter userDetails ) {
        Integer creatorId = userDetails.getId();
        Boolean template = templateManagement.createTemplate( templateMapper.mapFromTemplateRequestToTemplateConfig( templateRequest, creatorId ) );
        if ( template ) {
            return new ResponseEntity<>(  HttpStatus.CREATED );
        } else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }


    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_TEMPLATES.name())")
    public ResponseEntity<?> getAllTemplates( @AuthenticationPrincipal UserAdapter userDetails ) {
        List<TemplateResponse> templateConfiguration = templateManagement.getTemplateConfigurationById( userDetails.getId() );
        TemplateViewResponse templateViewResponse = new TemplateViewResponse( templateConfiguration );
        return new ResponseEntity<>( templateViewResponse, HttpStatus.OK );
    }



}
