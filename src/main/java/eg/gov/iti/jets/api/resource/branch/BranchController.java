package eg.gov.iti.jets.api.resource.branch;


import eg.gov.iti.jets.api.resource.ami.AmiRequest;
import eg.gov.iti.jets.api.resource.ami.AmiViewResponse;
import eg.gov.iti.jets.api.resource.template.SuccessResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.management.BranchManagement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    final BranchManagement branchManagementImpl;
    final Mapper mapper;

    public BranchController( BranchManagement branchManagementImpl, Mapper mapper){
        this.branchManagementImpl = branchManagementImpl;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<BranchResponse> getBranches(){
        return branchManagementImpl.getAllBranches()
                .stream().map( mapper::mapFromBranchToBranchResponse )
                .collect( Collectors.toList() );

    }

    @GetMapping("/{id}")
    public BranchViewResponse getBranchById(@PathVariable int id){
        Optional<Branch> branch = branchManagementImpl.getBranchById(id);
        return branch.map( value -> new BranchViewResponse( true, mapper.mapFromBranchToBranchResponse(value))).orElseGet( () -> new BranchViewResponse( false, null ) );
    }

    @PostMapping()
    public Branch createBranch( @RequestBody BranchRequest branchRequest){
        return branchManagementImpl.createBranch( mapper.mapFromBranchRequestToBranch( branchRequest )  );
    }


    @PutMapping
    public BranchResponse updateBranch (@RequestBody BranchRequest branchRequest){
        Branch branch = branchManagementImpl.updateBranch( mapper.mapFromBranchRequestToBranch(branchRequest) );
        return mapper.mapFromBranchToBranchResponse( branch );
    }

}
