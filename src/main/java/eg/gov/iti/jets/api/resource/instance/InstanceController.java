package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.management.InstanceManagement;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import eg.gov.iti.jets.service.util.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/instances")
public class InstanceController {

    private final InstanceManagement instanceManagement;
    private final InstanceMapper instanceMapper;

    public InstanceController( InstanceManagement instanceManagement, InstanceMapper instanceMapper ) {
        this.instanceManagement = instanceManagement;
        this.instanceMapper = instanceMapper;
    }


    // TODO test to see what gets returned, mahmoud will inform mariem of 200 OK being equivalent to boolean success and that exceptions should get thrown if response is error
    @PostMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).CREATE_TERMINATE_ASSIGN_INSTANCE.name())")
    ResponseEntity<?> createInstance(@RequestBody InstanceRequest instanceRequest, @AuthenticationPrincipal UserAdapter userDetails) {
        Integer creatorId = userDetails.getId();
        for ( Integer id :
                instanceRequest.getStudentIds() ) {
            Instance instance = instanceMapper.mapFromInstanceReqToInstance(instanceRequest ,id, creatorId);
            instanceManagement.createInstance(instance);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("start/{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_VIEW_INSTANCE.name())")
    ResponseEntity<?> startInstance(@PathVariable String instanceId) {
        String instanceState = instanceManagement.startInstance(instanceId);
        return new ResponseEntity<>( instanceState, HttpStatus.OK );
    }


    @PostMapping("stop/{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_VIEW_INSTANCE.name())")
    ResponseEntity<?> stopInstance(@PathVariable String instanceId) {
        String instanceState = instanceManagement.stopInstance(instanceId);
        return new ResponseEntity<>( instanceState, HttpStatus.OK );
    }


    @DeleteMapping("delete/{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).CREATE_TERMINATE_ASSIGN_INSTANCE.name())")
    ResponseEntity<?> deleteInstance(@PathVariable String instanceId) {
        String instanceState = instanceManagement.deleteInstance(instanceId);
        return new ResponseEntity<>( instanceState , HttpStatus.OK );
    }

    @GetMapping("{instanceId}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_VIEW_INSTANCE.name())")
    ResponseEntity<?> getDetails(@PathVariable String instanceId) {
        Instance instance = instanceManagement.getInstanceDetails(instanceId);
        InstanceResponse instanceResponse = instanceMapper.mapFromInstanceToInstanceResponse( instance );
        return new ResponseEntity<>( instanceResponse ,  HttpStatus.OK );
    }


    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_VIEW_INSTANCE.name())")
    ResponseEntity<?> getInstances(@AuthenticationPrincipal UserAdapter userDetails) {
        Integer userId = userDetails.getId();
        var instances = instanceManagement.getInstancesByUserId(userId);
        var instanceResponses = instances.stream().map( instanceMapper::mapFromInstanceToInstanceResponse ).collect( Collectors.toList() );
        var instanceObjectResponse = new InstanceObjectResponse( instanceResponses );
        return  new ResponseEntity<>( instanceObjectResponse , HttpStatus.OK );
    }


}
