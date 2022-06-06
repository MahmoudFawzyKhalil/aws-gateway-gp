package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.supervisor.SupervisorRequest;
import eg.gov.iti.jets.api.resource.supervisor.SupervisorResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.api.resource.user.UserRequest;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.persistence.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

//    public Supervisor mapFromSupervisorRequestToSupervisor(SupervisorRequest supervisorRequest) {
//        return  null;
//    }
//
//    public SupervisorResponse mapFromSupervisorToSupervisorResponse(Supervisor supervisor) {
//        return null;
//    }

    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest) {
        return null;
    }

    public TrackResponse mapFromTrackToTrackResponse(Track track) {
        return null;
    }

    public User mapFromUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return user;
    }

    public UserResponse mapFromUserToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().getName());
        response.setPassword(user.getPassword());
        response.setPrivileges(user.getRole().getPrivileges().stream().map(Privilege::getName).collect(Collectors.toList()));
        response.setTracks(null);
        response.setGrantedInstances(null);
        response.setCreatedInstances(null);
        return response;
    }

    public List<UserResponse> mapFromListOfUsersToListOfUserResponses(List<User> users){
        List<UserResponse> userResponses =
                users.stream().map(e -> this.mapFromUserToUserResponse(e)).collect(Collectors.toList());
        return userResponses;
    }

}
