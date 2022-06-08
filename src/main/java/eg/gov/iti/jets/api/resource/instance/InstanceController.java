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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).CREATE_TERMINATE_INSTANCE.name())")
    SuccessResponse createInstance( @RequestBody  InstanceRequest instanceRequest , @AuthenticationPrincipal UserAdapter userDetails){
        Integer id = userDetails.getId();
        Instance instance1 = mapper.mapFromInstanceReqToInstance( instanceRequest, id );
        Instance instance = instanceManagement.createInstance( instance1 );
        return new SuccessResponse(instance != null);
    }

    @GetMapping("/start/{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_INSTANCE.name())")
    SuccessResponse startInstance (@PathVariable String instanceId){
        String s = instanceManagement.startInstance( instanceId );
        System.out.println(s);
        return new SuccessResponse(true);
    }


    @GetMapping("stop/{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_INSTANCE.name())")
    SuccessResponse stopInstance (@PathVariable String instanceId){
        String s = instanceManagement.stopInstance( instanceId );
        return new SuccessResponse(true);
    }


    @DeleteMapping("/delete/{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).CREATE_TERMINATE_INSTANCE.name())")
    SuccessResponse deleteInstance (@PathVariable String instanceId){
        String s = instanceManagement.deleteInstance( instanceId );
        return new SuccessResponse(true);
    }

    @GetMapping("{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_INSTANCE.name())")
    InstanceResponse getDetails (@PathVariable String instanceId){
        Instance instance = instanceManagement.instanceDetails( instanceId );

        return mapper.mapFromInstanceToInstanceResponse( instance );
    }

    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_INSTANCE.name())")
    InstanceObjectResponse getInstances ( @AuthenticationPrincipal UserAdapter userDetails ){
        Integer id = userDetails.getId();
        List<InstanceResponse> list = new ArrayList<>();
        List<Instance> instancesByUserId = instanceManagement.getInstancesByUserId( id );
        for ( Instance instance:
              instancesByUserId ) {
            list.add( mapper.mapFromInstanceToInstanceResponse( instance ) );
        }
        return new InstanceObjectResponse(list);
    }


}
