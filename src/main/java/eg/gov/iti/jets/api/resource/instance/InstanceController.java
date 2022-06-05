package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
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

//List<>
    @GetMapping("types")
    ResponseEntity<List<String>> getInstanceTypes(){
        return  new ResponseEntity<>(instanceManagement.getInstanceTypes(), HttpStatus.OK);
    }



    @GetMapping("ami/{id}")
   ResponseEntity< Optional<Ami>>  describeAmi (@PathVariable String id){
        return new ResponseEntity<>(instanceManagement.describeAmi(id),HttpStatus.OK) ;
    }

    @PostMapping
    InstanceResponse createInstance(InstanceRequest instanceRequest){
        Optional<Instance> instance = instanceManagement.createInstance( instanceRequest.getTemplateId(), instanceRequest.getInstanceName(), instanceRequest.getKeyPair() );

        return mapper.mapFromInstanceToInstanceResponse( instance );
    }

}
