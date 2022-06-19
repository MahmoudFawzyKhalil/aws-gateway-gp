package eg.gov.iti.jets.api.resource.instance;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.management.InstanceManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/instances")
public class InstanceController {

    @Autowired
    MapperUtilForApi mapperUtilForApi;
    private final InstanceManagement instanceManagement;

    final Mapper mapper;

    public InstanceController(InstanceManagement instanceManagement, Mapper mapper) {
        this.instanceManagement = instanceManagement;
        this.mapper = mapper;
    }


    // TODO test to see what gets returned, mahmoud will inform mariem of 200 OK being equivalent to boolean success and that exceptions should get thrown if response is error
    @PostMapping
    ResponseEntity<?> createInstance(@RequestBody InstanceRequest instanceRequest, @AuthenticationPrincipal UserAdapter userDetails) {
        Integer creatorId = userDetails.getId();
        for ( Integer studentId : instanceRequest.getStudentIds() ) {
            Instance instance = mapper.mapFromInstanceReqToInstance(instanceRequest , studentId, creatorId);
            instanceManagement.createInstance(instance);
        }
        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @GetMapping("start/{instanceId}")
    SuccessResponse startInstance(@PathVariable String instanceId) {
        String s = instanceManagement.startInstance(instanceId);
        System.out.println(s);
        return new SuccessResponse(true);
    }


    @GetMapping("stop/{instanceId}")
    SuccessResponse stopInstance(@PathVariable String instanceId) {
        String s = instanceManagement.stopInstance(instanceId);
        return new SuccessResponse(true);
    }


    @DeleteMapping("delete/{instanceId}")
    SuccessResponse deleteInstance(@PathVariable String instanceId) {
        String s = instanceManagement.deleteInstance(instanceId);
        return new SuccessResponse(true);
    }

    @GetMapping("{instanceId}")
    InstanceResponse getDetails(@PathVariable String instanceId) {
        System.out.println(instanceId);
        Instance instance = instanceManagement.getInstanceDetails(instanceId);

        return mapper.mapFromInstanceToInstanceResponse(instance);
    }

//    @GetMapping
//    InstanceObjectResponse getInstances(@AuthenticationPrincipal UserAdapter userDetails) {
//        Integer id = userDetails.getId();
//        List<InstanceResponse> list = new ArrayList<>();
//        List<Instance> instancesByUserId = instanceManagement.getInstancesByUserId(id);
//        for (Instance instance :
//                instancesByUserId) {
//            list.add(mapper.mapFromInstanceToInstanceResponse(instance));
//        }
//        return new InstanceObjectResponse(list);
//    }


}
