package eg.gov.iti.jets.api.resource.branch;

import eg.gov.iti.jets.api.resource.trainingProgram.GetTrainingProgramsResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.management.BranchManagement;
import eg.gov.iti.jets.service.management.TrainingProgramManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    final BranchManagement branchManagement;
    final Mapper mapper;
    final TrainingProgramManagement trainingProgramManagement;

    public BranchController( BranchManagement branchManagementImpl, Mapper mapper, TrainingProgramManagement trainingProgramManagement ){
        this.branchManagement = branchManagementImpl;
        this.mapper = mapper;
        this.trainingProgramManagement = trainingProgramManagement;
    }


    @GetMapping
    public ResponseEntity<BranchResponseList> getBranches(){
        List<Branch> branches = branchManagement.getAllBranches();
        List<BranchResponse> branchResponses =  mapper.mapFromListOfBranchToListOfBranchResponses(branches);
        BranchResponseList branchResponseList = new BranchResponseList();
        for(BranchResponse response : branchResponses){
            branchResponseList.getBranchResponsesList().add(response);
        }
        return new ResponseEntity<>( branchResponseList , HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getBranchById(@PathVariable int id){
        Optional<Branch> branch = branchManagement.getBranchById(id);
        BranchResponse branchResponse = new BranchResponse();
        if(branch.isPresent()){
            branchResponse = mapper.mapFromBranchToBranchResponse( branch.get() );
        }
        return new ResponseEntity<>( branchResponse ,HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<BranchResponse> createBranch(@RequestBody BranchRequest branchRequest){
        Branch branch = branchManagement.createBranch( mapper.mapFromBranchRequestToBranch( branchRequest ) );
        BranchResponse branchResponse = mapper.mapFromBranchToBranchResponse( branch );
        return new ResponseEntity<>(branchResponse , HttpStatus.CREATED ) ;
    }



    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> updateBranch (@PathVariable int id , @RequestBody BranchPutRequest branchPutRequest){
        Branch branch = branchManagement.updateBranch( mapper.mapFromBranchPutRequestToBranch(branchPutRequest , id) );
        BranchResponse branchResponse = mapper.mapFromBranchToBranchResponse( branch );
        return new ResponseEntity<>( branchResponse , HttpStatus.OK );
    }

    @PatchMapping  ("/{id}")
    public ResponseEntity<BranchResponse> changeStatus (@PathVariable int id , @RequestParam boolean branchStatus){
        Branch branch = branchManagement.updateBranch( mapper.mapFromBranchPatchRequestToBranch(branchStatus , id) );
        BranchResponse branchResponse = mapper.mapFromBranchToBranchResponse( branch );
        return new ResponseEntity<>( branchResponse , HttpStatus.OK );
    }

    @GetMapping("{branchId}/trainingPrograms")
    public ResponseEntity<GetTrainingProgramsResponse> getTrainingProgramsByBranchId( @PathVariable int branchId){
        List<TrainingProgram> trainingProgramByBranchId = branchManagement.getTrainingProgramByBranchId( branchId );
        List<TrainingProgramResponse> trainingProgramResponse = new ArrayList<>();
        trainingProgramByBranchId.forEach( trainingProgram -> trainingProgramResponse.add( mapper.mapFromTrainingProgramToTrainingProgramResponse( trainingProgram ) ) );
        GetTrainingProgramsResponse trainingProgramsResponse = new GetTrainingProgramsResponse( trainingProgramResponse );
        return new ResponseEntity<>(  trainingProgramsResponse  , HttpStatus.OK);
    }

}
