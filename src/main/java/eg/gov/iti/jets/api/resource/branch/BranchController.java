package eg.gov.iti.jets.api.resource.branch;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.BranchManagement;
import eg.gov.iti.jets.service.model.Branch;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return branchManagement.createBranch( mapper.mapFromBranchRequestToBranch( branchRequest )  );
    }

    @PutMapping
    public BranchResponse updateBranch (@RequestBody BranchRequest branchRequest){
        Branch branch = branchManagement.updateBranch( mapper.mapFromBranchRequestToBranch( branchRequest ) );
        return mapper.mapFromBranchToBranchResponse( branch );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteBranch( @PathVariable int id){
        return branchManagement.deleteBranch( id );
    }

    @GetMapping("/{id}")
    public BranchResponse getBranchById(@PathVariable int id){
        return mapper.mapFromBranchToBranchResponse( branchManagement.getBranchById( id ) );
    }

    @GetMapping
    public List<BranchResponse> getBranches(){
        return branchManagement.getAllBranches()
                .stream().map( mapper::mapFromBranchToBranchResponse )
                .collect( Collectors.toList() );
    }

}
