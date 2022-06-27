package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.service.management.TemplateManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TEMPLATES.name())")
    public ResponseEntity<?> createTemplate(@Valid @RequestBody TemplateRequest templateRequest, @AuthenticationPrincipal UserAdapter userDetails ) {
        Integer creatorId = userDetails.getId();
        templateManagement.createTemplate( templateMapper.mapFromTemplateRequestToTemplateConfig( templateRequest, creatorId ) );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize( "hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TEMPLATES.name())" )
    public ResponseEntity<?> assignTemplate(@Valid @RequestBody TemplateAssignRequest templateAssignRequest){
        templateManagement.assignTemplates(templateAssignRequest);
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_TEMPLATES.name())")
    public ResponseEntity<?> getAllTemplates( @AuthenticationPrincipal UserAdapter userDetails ) {
        List<TemplateResponse> templateConfiguration = templateManagement.getTemplateConfigurationById( userDetails.getId() );
        TemplateViewResponse templateViewResponse = new TemplateViewResponse( templateConfiguration );
        return new ResponseEntity<>( templateViewResponse, HttpStatus.OK );
    }



}
