package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.instance.InstanceResponse;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.template.TemplateRequest;
import eg.gov.iti.jets.api.resource.template.TemplateResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mapper {
    public Branch mapFromBranchRequestToBranch( BranchRequest branchRequest){
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



    public IntakeResponse mapFromIntakeToIntakeResponse( Intake intake) {
        return null;
    }

    public Intake mapFromIntakeRequestToIntake(IntakeRequest intakeRequest){return null;}


    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest) {
        return null;
    }

    public TrackResponse mapFromTrackToTrackResponse(Track track) {
        return null;
    }

    public TemplateConfiguration mapFromTemplateRequestToTemplate( TemplateRequest templateRequest) {
        return null;
    }

    public TemplateResponse mapFromTemplateToTemplateResponse( TemplateConfiguration templateConfiguration) {
        return null;
    }
    public Instance mapFromInstanceRequestToInstance( IntakeRequest intakeRequest) {
        return null;
    }

    public InstanceResponse mapFromInstanceToInstanceResponse( Optional<Instance> instance) {
        InstanceResponse instanceResponse = new InstanceResponse();
        instanceResponse.setSuccess( instance.isPresent() );
        return instanceResponse;
    }
}
