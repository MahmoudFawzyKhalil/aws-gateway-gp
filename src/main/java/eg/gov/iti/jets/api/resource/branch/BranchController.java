package eg.gov.iti.jets.api.resource.branch;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.BranchManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    final BranchManagement branchManagement;
    final Mapper mapper;

    public BranchController( BranchManagement branchManagement , Mapper mapper){
        this.branchManagement = branchManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public Boolean createBranch( @RequestBody BranchRequest branchRequest){
        return branchManagement.createBranch( mapper.mapFromBranchRequestToBranchResponse( branchRequest )  );
    }
}
