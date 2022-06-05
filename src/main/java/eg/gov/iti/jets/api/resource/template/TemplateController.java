package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Secured("MANAGE_TEMPLATE")
    public SuccessResponse createTemplate(@RequestBody TemplateRequest templateRequest ){
       return new SuccessResponse(templateManagement.createTemplate(mapper.mapFromTemplateRequestToTemplateConfig(templateRequest)));
    }

    @DeleteMapping("/{id}")
    @Secured("MANAGE_TEMPLATE")
    public SuccessResponse deleteTemplate ( @PathVariable int id ){
        return new SuccessResponse(templateManagement.deleteTemplate( id )) ;
    }


    @GetMapping
    @Secured("VIEW_TEMPLATES")
    // TODO: 6/5/2022 get the Id?
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

    @GetMapping("/subnet")
    @Secured("MANAGE_TEMPLATE")
    SubnetResponse getAllSubnet(){
        return  mapper.mapFromSubnetToSubnetResponse(templateManagement.getAllSubnet());

    }

    @PostMapping("/ami")
    @Secured("MANAGE_TEMPLATE")
    public AmiViewResponse getAmi(@RequestBody AmiRequest amiRequest){
        Optional<Ami> ami = templateManagement.describeAmi( amiRequest.getAmiId() );
        return ami.map( value -> new AmiViewResponse( true, mapper.mapFromAmiToAmiResponse( value ) ) ).orElseGet( () -> new AmiViewResponse( false, null ) );
    }

    @GetMapping("/types")
    @Secured("MANAGE_TEMPLATE")
    InstanceTypeResponse getInstanceTypes(){
        return  new InstanceTypeResponse(templateManagement.getInstanceTypes());
    }

    @GetMapping("/securitygroup/{id}")
    @Secured("MANAGE_TEMPLATE")
    SecurityGroupObjectResponse getSecurityGroups(@PathVariable String id){
        List<SecurityGroup> securityGroups= templateManagement.describeSecurityGroupsForVpc(id);
        List<SecurityGroupResponse> securityGroupResponses = new ArrayList<>();
        for(SecurityGroup group:securityGroups){
            securityGroupResponses.add(mapper.mapFromSecurityGroupToSecurityGroupResponse(group));
        }
        return new SecurityGroupObjectResponse(securityGroupResponses);
    }

    // TODO: 6/2/2022 3ayzen bs two methods mkmlesh wala 3ala hasab mariam
}
