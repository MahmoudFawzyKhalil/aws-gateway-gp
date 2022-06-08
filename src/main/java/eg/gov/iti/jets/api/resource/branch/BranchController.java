package eg.gov.iti.jets.api.resource.branch;


import eg.gov.iti.jets.api.resource.ami.AmiRequest;
import eg.gov.iti.jets.api.resource.ami.AmiViewResponse;
import eg.gov.iti.jets.api.resource.template.SuccessResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.track.TrackResponseList;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.management.BranchManagement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    final BranchManagement branchManagement;
    final Mapper mapper;

    public BranchController( BranchManagement branchManagementImpl, Mapper mapper){
        this.branchManagement = branchManagementImpl;
        this.mapper = mapper;
    }


    @GetMapping
    public BranchResponseList getBranches(){
        List<Branch> branches = branchManagement.getAllBranches();
        List<BranchResponse> branchResponses =  mapper.mapFromListOfBranchToListOfBranchResponses(branches);
        BranchResponseList branchResponseList = new BranchResponseList();
        for(BranchResponse response : branchResponses){
            branchResponseList.getBranchResponsesList().add(response);
        }
        return branchResponseList;
    }

    @GetMapping("/{id}")
    public BranchViewResponse getBranchById(@PathVariable int id){
        Optional<Branch> branch = branchManagement.getBranchById(id);
        return branch.map( value -> new BranchViewResponse( true, mapper.mapFromBranchToBranchResponse(value))).orElseGet( () -> new BranchViewResponse( false, null ) );
    }

    @PostMapping
    public BranchResponse createBranch(@RequestBody BranchRequest branchRequest){
        Branch branch = branchManagement.createBranch( mapper.mapFromBranchRequestToBranch( branchRequest ) );
        return mapper.mapFromBranchToBranchResponse(branch);
    }



    @PutMapping
    public BranchResponse updateBranch (@RequestBody BranchRequest branchRequest){
        Branch branch = branchManagement.updateBranch( mapper.mapFromBranchRequestToBranch(branchRequest) );
        return mapper.mapFromBranchToBranchResponse( branch );
    }

}
