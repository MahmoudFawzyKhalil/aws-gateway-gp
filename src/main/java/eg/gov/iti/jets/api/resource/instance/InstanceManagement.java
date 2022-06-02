package eg.gov.iti.jets.api.resource.instance;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/instances")
public class InstanceManagement {

    private final InstanceManagement instanceManagement;

    public InstanceManagement(InstanceManagement instanceManagement) {
        this.instanceManagement = instanceManagement;
    }

    @PostMapping("create")
    void createInstances(){
        instanceManagement.createInstances();
    }

    @DeleteMapping("delete")
    void deleteInstances(){
        instanceManagement.deleteInstances();
    }

    @GetMapping
    void getAllInstances(){
        instanceManagement.getAllInstances();
    }

    @GetMapping("{branchName}")
    void getAllBranchInstances(@PathVariable String branchName){
        instanceManagement.getAllBranchInstances(branchName);
    }

    @GetMapping ("{trackName}")
    void getAllTrackInstances(@PathVariable String trackName){
        instanceManagement.getAllTrackInstances(trackName);
    }








}
