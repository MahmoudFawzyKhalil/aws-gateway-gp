package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.service.model.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Branch mapFromBranchRequestToBranch(BranchRequest branchRequest){
        return null;
    }

    public BranchResponse mapFromBranchToBranchResponse(Branch branch){
        return null;
    }
    public TrainingProgram mapFromTrainingProgramRequestToTrainingProgram( TrainingProgramRequest trainingProgramRequest){
        return null;
    }

    public TrainingProgramResponse mapFromTrainingProgramToTrainingProgramResponse( TrainingProgram trainingProgram){
        return null;
    }


}
