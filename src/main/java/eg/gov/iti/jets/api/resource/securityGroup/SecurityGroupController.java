package eg.gov.iti.jets.api.resource.securityGroup;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.management.SecurityGroupAws;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/securityGroup")
// TODO: 6/17/2022 Ashraf dh keda bado supervisor
public class SecurityGroupController {
    private final SecurityGroupMapper securityGroupMapper;
    private final
    SecurityGroupAws securityGroupAws;

    public SecurityGroupController( SecurityGroupMapper securityGroupMapper, SecurityGroupAws securityGroupAws ) {
        this.securityGroupMapper = securityGroupMapper;
        this.securityGroupAws = securityGroupAws;
    }


    @GetMapping("{vpcId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TEMPLATE.name())")
    ResponseEntity<?> getSecurityGroups( @PathVariable String vpcId){
        List<SecurityGroup> securityGroups= securityGroupAws.describeSecurityGroupsForVpc(vpcId);
        List<SecurityGroupResponse> securityGroupResponseList = securityGroups.stream().map( securityGroupMapper::mapFromSecurityGroupToSecurityGroupResponse ).collect( Collectors.toList() );
        SecurityGroupObjectResponse securityGroupObjectResponse = new SecurityGroupObjectResponse( securityGroupResponseList );
        return new ResponseEntity<>( securityGroupObjectResponse , HttpStatus.OK );
    }

}
