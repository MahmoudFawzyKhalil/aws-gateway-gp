package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.service.management.InstanceManagement;
import org.springframework.web.bind.annotation.*;

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

//List<>
    @GetMapping("types")
    List<String> getInstanceTypes(){
        return  instanceManagement.getInstanceTypes();
    }

    // return List<String>
    @GetMapping("subnet")
    List<Subnet> getAllSubnet(){
        return  instanceManagement.getAllSubnet();
    }

    // retun List<String> securitygroup


    @GetMapping("ami/{id}")
    Optional<Ami> describeAmi (@PathVariable String id){
        return  instanceManagement.describeAmi(id);
    }

    @PostMapping
    InstanceResponse createInstance(InstanceRequest instanceRequest){
        Optional<Instance> instance = instanceManagement.createInstance( instanceRequest.getTemplateId(), instanceRequest.getInstanceName(), instanceRequest.getKeyPair() );

        return mapper.mapFromInstanceToInstanceResponse( instance );
    }

}
