package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    @GetMapping("subnet")
    SubnetResponse getAllSubnet(){
        return  mapper.mapFromSubnetToSubnetResponse(templateManagement.getAllSubnet());

    }

    @PostMapping("/ami")
    public AmiViewResponse getAmi(@RequestBody AmiRequest amiRequest){
        Optional<Ami> ami = templateManagement.describeAmi( amiRequest.getAmiId() );
        return ami.map( value -> new AmiViewResponse( true, mapper.mapFromAmiToAmiResponse( value ) ) ).orElseGet( () -> new AmiViewResponse( false, null ) );
    }

    @GetMapping("types")
    ResponseEntity<List<String>> getInstanceTypes(){
        return  new ResponseEntity<>(templateManagement.getInstanceTypes(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<List<SecurityGroupResponse>>getSecurityGroups(@PathVariable String id){
        List<SecurityGroup> securityGroups= templateManagement.describeSecurityGroupsForVpc(id);
        List<SecurityGroupResponse> securityGroupResponses = new ArrayList<>();
        for(SecurityGroup group:securityGroups){
            securityGroupResponses.add(mapper.mapFromSecurityGroupToSecurityGroupResponse(group));
        }
        return new ResponseEntity<>(securityGroupResponses,HttpStatus.OK);
    }

    // TODO: 6/2/2022 3ayzen bs two methods mkmlesh wala 3ala hasab mariam
}
