package eg.gov.iti.jets.api.resource.instance;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.management.InstanceManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<?> createInstance(@RequestBody InstanceRequest instanceRequest, @AuthenticationPrincipal UserAdapter userDetails) {
        Integer creatorId = userDetails.getId();
        Instance instance = instanceMapper.mapFromInstanceReqToInstance(instanceRequest, creatorId);
        instanceManagement.createInstance(instance);
        return ResponseEntity.ok().build();
    }

    @GetMapping("start/{instanceId}")
    ResponseEntity<?> startInstance(@PathVariable String instanceId) {
        String instanceState = instanceManagement.startInstance(instanceId);
        return new ResponseEntity<>( instanceState, HttpStatus.OK );
    }


    @GetMapping("stop/{instanceId}")
    ResponseEntity<?> stopInstance(@PathVariable String instanceId) {
        String instanceState = instanceManagement.stopInstance(instanceId);
        return new ResponseEntity<>( instanceState, HttpStatus.OK );
    }


    @DeleteMapping("delete/{instanceId}")
    ResponseEntity<?> deleteInstance(@PathVariable String instanceId) {
        String instanceState = instanceManagement.deleteInstance(instanceId);
        return new ResponseEntity<>( instanceState , HttpStatus.OK );
    }

    @GetMapping("{instanceId}")
    ResponseEntity<?> getDetails(@PathVariable String instanceId) {
        Instance instance = instanceManagement.getInstanceDetails(instanceId);
        InstanceResponse instanceResponse = instanceMapper.mapFromInstanceToInstanceResponse( instance );
        return new ResponseEntity<>( instanceResponse ,  HttpStatus.OK );
    }

    @GetMapping
    ResponseEntity<?> getInstances(@AuthenticationPrincipal UserAdapter userDetails) {
        Integer userId = userDetails.getId();
        var instances = instanceManagement.getInstancesByUserId(userId);
        var instanceResponses = instances.stream().map( instanceMapper::mapFromInstanceToInstanceResponse ).collect( Collectors.toList() );
        var instanceObjectResponse = new InstanceObjectResponse( instanceResponses );

        return new ResponseEntity<>( instanceObjectResponse , HttpStatus.OK );
    }


}
