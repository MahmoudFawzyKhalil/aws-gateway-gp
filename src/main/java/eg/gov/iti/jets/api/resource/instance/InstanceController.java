package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.service.management.InstanceManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/instances")
public class InstanceController {

    private final InstanceManagement instanceManagement;

    final Mapper mapper;

    public InstanceController( InstanceManagement instanceManagement, Mapper mapper ) {
        this.instanceManagement = instanceManagement;
        this.mapper = mapper;
    }


    @GetMapping("types")
    ResponseEntity<List<String>> getInstanceTypes(){
        return  new ResponseEntity<>(instanceManagement.getInstanceTypes(), HttpStatus.OK);
    }


    @GetMapping("subnet")
    SubnetResponse getAllSubnet(){
        return  mapper.mapFromSubnetToSubnetResponse(instanceManagement.getAllSubnet());

    }


    @GetMapping("{id}")
    ResponseEntity<List<SecurityGroupResponse>>getSecurityGroups(@PathVariable String id){
        List<SecurityGroup> securityGroups= instanceManagement.describeSecurityGroupsForVpc(id);
        List<SecurityGroupResponse> securityGroupResponses = new ArrayList<>();
       for(SecurityGroup group:securityGroups){
           securityGroupResponses.add(mapper.mapFromSecurityGroupToSecurityGroupResponse(group));
       }
        return new ResponseEntity<>(securityGroupResponses,HttpStatus.OK);
    }
    @GetMapping("ami/{id}")
    ResponseEntity< Optional<Ami>>  describeAmi (@PathVariable String id){
        return new ResponseEntity<>(instanceManagement.describeAmi(id),HttpStatus.OK) ;
    }

    //post
    //res success


    @PostMapping
    InstanceResponse createInstance(InstanceRequest instanceRequest){
        Optional<Instance> instance = instanceManagement.createInstance( instanceRequest.getTemplateId(), instanceRequest.getInstanceName(), instanceRequest.getKeyPair() );

        return mapper.mapFromInstanceToInstanceResponse( instance );
    }

}
