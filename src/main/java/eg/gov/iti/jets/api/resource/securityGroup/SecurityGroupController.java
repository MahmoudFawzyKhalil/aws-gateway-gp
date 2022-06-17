package eg.gov.iti.jets.api.resource.securityGroup;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.management.SecurityGroupAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/SecurityGroup")
public class SecurityGroupController {
    @Autowired
    Mapper mapper;
    @Autowired
    SecurityGroupAws securityGroupAws;
    @GetMapping("{vpcId}")
    SecurityGroupObjectResponse getSecurityGroups( @PathVariable String vpcId){

        List<SecurityGroup> securityGroups= securityGroupAws.describeSecurityGroupsForVpc(vpcId);
        List<SecurityGroupResponse> securityGroupResponses = new ArrayList<>();
        for(SecurityGroup group:securityGroups){
            securityGroupResponses.add(mapper.mapFromSecurityGroupToSecurityGroupResponse(group));
        }
        return new SecurityGroupObjectResponse(securityGroupResponses);
    }
}
