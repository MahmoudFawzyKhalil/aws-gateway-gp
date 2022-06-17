package eg.gov.iti.jets.api.resource.securityGroup;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.management.SecurityGroupAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/securityGroup")
// TODO: 6/17/2022 Ashraf dh keda bado supervisor
public class SecurityGroupController {
    final
    Mapper mapper;
    final
    SecurityGroupAws securityGroupAws;

    public SecurityGroupController( Mapper mapper, SecurityGroupAws securityGroupAws ) {
        this.mapper = mapper;
        this.securityGroupAws = securityGroupAws;
    }

    @GetMapping("{vpcId}")
        // TODO: 6/17/2022 lw null aw msh sah error??
    ResponseEntity<?> getSecurityGroups( @PathVariable String vpcId){
        // TODO: 6/17/2022 mmkn hena myrga3sh haga ??
        List<SecurityGroup> securityGroups= securityGroupAws.describeSecurityGroupsForVpc(vpcId);
        List<SecurityGroupResponse> securityGroupResponses = new ArrayList<>();
        for(SecurityGroup group:securityGroups){
            securityGroupResponses.add(mapper.mapFromSecurityGroupToSecurityGroupResponse(group));
        }
        SecurityGroupObjectResponse securityGroupObjectResponse = new SecurityGroupObjectResponse( securityGroupResponses );
        return new ResponseEntity<>( securityGroupObjectResponse , HttpStatus.OK );
    }

}
