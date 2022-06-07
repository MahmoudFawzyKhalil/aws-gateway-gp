package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.management.InstanceManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    SuccessResponse createInstance( @RequestBody  InstanceRequest instanceRequest , @AuthenticationPrincipal UserAdapter userDetails){
        Integer id = userDetails.getId();
        Instance instance1 = mapper.mapFromInstanceReqToInstance( instanceRequest, id );
        Instance instance = instanceManagement.createInstance( instance1 );
        return new SuccessResponse(instance != null);
    }

    @GetMapping("start/{instanceId}")
    SuccessResponse startInstance (@PathVariable String instanceId){
        String s = instanceManagement.startInstance( instanceId );
        System.out.println(s);
        return new SuccessResponse(true);
    }


    @GetMapping("stop/{instanceId}")
    SuccessResponse stopInstance (@PathVariable String instanceId){
        String s = instanceManagement.stopInstance( instanceId );
        return new SuccessResponse(true);
    }


    @DeleteMapping("delete/{instanceId}")
    SuccessResponse deleteInstance (@PathVariable String instanceId){
        String s = instanceManagement.stopInstance( instanceId );
        return new SuccessResponse(true);
    }

    @GetMapping("{instanceId}")
    InstanceResponse getDetails (@PathVariable String instanceId){
        Instance instance = instanceManagement.instanceDetails( instanceId );

        return mapper.mapFromInstanceToInstanceResponse( instance );
    }

    @GetMapping
    Boolean getInstances ( @AuthenticationPrincipal UserDetails userDetails ){
        return null;
    }

}
