package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.management.InstanceManagement;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/instances")
public class InstanceController {

    @Autowired
    MapperUtilForApi mapperUtilForApi;
    private final InstanceManagement instanceManagement;

    final Mapper mapper;

    public InstanceController( InstanceManagement instanceManagement, Mapper mapper ) {
        this.instanceManagement = instanceManagement;
        this.mapper = mapper;
    }


    @PostMapping
    InstanceResponse createInstance( @RequestBody  InstanceRequest instanceRequest){

        Optional<Instance> instance = instanceManagement.createInstance( instanceRequest.getTemplateId(), instanceRequest.getInstanceName(), instanceRequest.getKeyPair() ,mapperUtilForApi.getUser( 1 ) );

        return mapper.mapFromInstanceToInstanceResponse( instance );
    }

    @GetMapping("{instanceId}")
    SuccessResponse startInstance (@PathVariable String instanceId){
        instanceManagement.startInstance(instanceId);
        return new SuccessResponse(true);
    }


    @GetMapping("{instanceId}")
    SuccessResponse stopInstance (@PathVariable String instanceId){
        instanceManagement.stopInstance(instanceId);
        return new SuccessResponse(true);
    }

    @DeleteMapping("{instanceId}")
    SuccessResponse deleteInstance (@PathVariable String instanceId){
        instanceManagement.stopInstance(instanceId);
        return new SuccessResponse(true);
    }
}
