package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchPutRequest;
import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.intake.IntakePutRequest;
import eg.gov.iti.jets.api.resource.role.*;
import eg.gov.iti.jets.api.resource.student.StudentResponse;
import eg.gov.iti.jets.api.resource.student.StudentRequest;
import eg.gov.iti.jets.api.resource.subnet.SubnetObjectResponse;
import eg.gov.iti.jets.api.resource.subnet.SubnetResponse;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.privilege.AddPrivilegeRequest;
import eg.gov.iti.jets.api.resource.privilege.GetPrivilegeResponse;
import eg.gov.iti.jets.api.resource.track.TrackPutRequest;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramPutRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.enums.BranchStatus;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    @Autowired
    private MapperUtilForApi mapperUtilForApi;












    public Branch mapFromBranchRequestToBranch( BranchRequest branchRequest ) {
        Branch branch = new Branch();
        branch.setAddress( branchRequest.getAddress() );
        branch.setName( branchRequest.getName() );
        return branch;
    }

    public BranchResponse mapFromBranchToBranchResponse( Branch branch ) {
        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setAddress( branch.getAddress() );
        branchResponse.setName( branch.getName() );
        branchResponse.setId( branch.getId() );
        if ( branch.getStatus() == BranchStatus.ACTIVE ) {
            branchResponse.setBranchStatus( true );
        } else {
            branchResponse.setBranchStatus( false );
        }
        return branchResponse;
    }

    public List<BranchResponse> mapFromListOfBranchToListOfBranchResponses( List<Branch> branches ) {
        List<BranchResponse> branchResponses =
                branches.stream().map( e -> this.mapFromBranchToBranchResponse( e ) ).collect( Collectors.toList() );
        return branchResponses;
    }

    public TrainingProgram mapFromTrainingProgramRequestToTrainingProgram( TrainingProgramRequest trainingProgramRequest ) {
        TrainingProgram trainingProgram = new TrainingProgram();
        Branch branch = mapperUtilForApi.getBranchById( trainingProgramRequest.getBranchId() );
        trainingProgram.setBranch( branch );
        trainingProgram.setName( trainingProgramRequest.getName() );
        return trainingProgram;
    }

    public TrainingProgramResponse mapFromTrainingProgramToTrainingProgramResponse( TrainingProgram trainingProgram ) {
        TrainingProgramResponse trainingProgramResponse = new TrainingProgramResponse();
        trainingProgramResponse.setBranchName( trainingProgram.getBranch().getName() );
        trainingProgramResponse.setName( trainingProgram.getName() );
        trainingProgramResponse.setId( trainingProgram.getId() );

        return trainingProgramResponse;
    }


    public IntakeResponse mapFromIntakeToIntakeResponse( Intake intake ) {
        IntakeResponse intakeResponse = new IntakeResponse();
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById( intake.getTrainingProgram().getId() );
        intakeResponse.setTrainingProgram( trainingProgram.getName() );
        intakeResponse.setIntakeName( intake.getName() );
        intakeResponse.setIntakeDescription( intake.getDescription() );
        intakeResponse.setId( intake.getId() );
        return intakeResponse;
    }


    public Intake mapFromIntakeRequestToIntake( IntakeRequest intakeRequest ) {
        Intake intake = new Intake();
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById( intakeRequest.getTrainingProgramId() );
        intake.setTrainingProgram( trainingProgram );
//        intake.setId( intakeRequest.getId() );
        intake.setDescription( intakeRequest.getIntakeDescription() );
        intake.setName( intakeRequest.getIntakeName() );
        return intake;
    }

    public Intake mapFromIntakePutRequestToIntake( int id, IntakePutRequest intakePutRequest ) {
        Intake intake = mapperUtilForApi.getIntakeById( id );
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById( intakePutRequest.getTrainingProgramId() );
        intake.setTrainingProgram( trainingProgram );
        intake.setDescription( intakePutRequest.getIntakeDescription() );
        intake.setName( intakePutRequest.getIntakeName() );
        return intake;
    }


    public List<IntakeResponse> mapFromListOfIntakesToListOfIntakeResponses( List<Intake> intakes ) {
        List<IntakeResponse> intakeResponses =
                intakes.stream().map( e -> this.mapFromIntakeToIntakeResponse( e ) ).collect( Collectors.toList() );
        return intakeResponses;
    }


    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest ) {
        Track track = new Track();
        track.setName( trackRequest.getName() );
        Intake intake = mapperUtilForApi.getIntakeById( trackRequest.getIntakeId() );
        track.setIntake( intake );
        return track;
    }


    public TrackResponse mapFromTrackToTrackResponse( Track track ) {
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setName( track.getName() );
        trackResponse.setId( track.getId() );
        trackResponse.setIntakeId( track.getIntake().getId() );
        return trackResponse;
    }


    public List<TrackResponse> mapFromListOfTracksToListOfTrackResponses( List<Track> tracks ) {
        List<TrackResponse> trackResponses =
                tracks.stream().map( e -> this.mapFromTrackToTrackResponse( e ) ).collect( Collectors.toList() );
        return trackResponses;
    }

    public Track mapFromTrackPutRequestToTrack( TrackPutRequest trackPutRequest, int id ) {
        try {
            Track track = mapperUtilForApi.getTrackById( id );
            track.setName( trackPutRequest.getName() );
            return track;
        } catch ( Exception e ) {
            throw new ResourceNotFoundException( "Could not update track with id " + id + " because it is not found" );
        }
    }


    public Instance mapFromInstanceRequestToInstance( IntakeRequest intakeRequest ) {
        return null;
    }


//    public Instance mapFromInstanceRequestToInstance( InstanceRequest instanceRequest ) {
//
//        return instanceResponse;
//    }

    public SubnetObjectResponse mapFromSubnetToSubnetResponse( List<Subnet> subnets ) {
        List<SubnetResponse> list = new ArrayList<>();
        for ( Subnet subnetResponse : subnets
        ) {
            SubnetResponse subnetResponse1 = new SubnetResponse( subnetResponse.getSubnetId(), subnetResponse.getVpcId()
                    , subnetResponse.getSubnetId(), subnetResponse.getAvailabilityZoneId(), subnetResponse.getAvailableIpAddressCount()
                    , subnetResponse.getCidrBlock(), subnetResponse.getMapPublicIpOnLaunch() );
            list.add( subnetResponse1 );
        }
        SubnetObjectResponse subnetObjectResponse = new SubnetObjectResponse( list );

        return subnetObjectResponse;
    }


    public GetPrivilegeResponse privilegeToGetPrivilegeResponse( Privilege privilege ) {
        return new GetPrivilegeResponse( privilege.getId(), privilege.getName().name() );
    }

    public Privilege addPrivilegeRequestToPrivilege( AddPrivilegeRequest addPrivilegeRequest ) {
        Privilege privilege = new Privilege();
        privilege.setName(
                PrivilegeName.valueOf( addPrivilegeRequest.getName() )
        );
        return privilege;
    }

    public Role roleRequestToRole( RoleRequest roleRequest ) {
        Role role = new Role();
        role.setName( roleRequest.getName() );
        role.setPrivileges(
                roleRequest.getPrivileges().stream().map( id -> {
                    Privilege privilege = new Privilege();
                    privilege.setId( id );
                    return privilege;
                } ).collect( Collectors.toList() )
        );
        return role;
    }

    public GetRoleResponse roleToGetRoleResponse( Role role ) {
        GetRoleResponse getRoleResponse = new GetRoleResponse();
        getRoleResponse.setId( role.getId() );
        getRoleResponse.setName( role.getName() );
        getRoleResponse.setPrivileges( role.getPrivileges().stream().map(
                privilege -> {
                    PrivilegeType privilegeRoleType = new PrivilegeType();
                    privilegeRoleType.setId( privilege.getId() );
                    privilegeRoleType.setName( privilege.getName().name() );
                    return privilegeRoleType;
                }
        ).collect( Collectors.toList() ) );
        return getRoleResponse;
    }

    public RoleResponse roleToRoleResponse( Role role ) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId( role.getId() );
        roleResponse.setName( role.getName() );
        roleResponse.setPrivileges(
                role.getPrivileges().stream().map( Privilege::getId ).collect( Collectors.toList() )
        );
        return roleResponse;
    }

    public UserResponse mapFromUserToUserResponse( User user ) {
        UserResponse response = new UserResponse();
        response.setUsername( user.getUsername() );
        response.setEmail( user.getEmail() );
        response.setRole( user.getRole().getName() );
        return response;
    }

    public List<UserResponse> mapFromListOfUsersToListOfUserResponses( List<User> users ) {
        return users.stream().map( this::mapFromUserToUserResponse ).collect( Collectors.toList() );
    }


    public TrainingProgram mapFromTrainingProgramPutRequestToTrainingProgram( TrainingProgramPutRequest trainingProgramPutRequest, int id ) {
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById( id );
        Branch branch = mapperUtilForApi.getBranchById( trainingProgramPutRequest.getBranchId() );
        trainingProgram.setBranch( branch );
        trainingProgram.setName( trainingProgramPutRequest.getName() );

        return trainingProgram;
    }

    public Branch mapFromBranchPutRequestToBranch( BranchPutRequest branchPutRequest, int id ) {
        try {
            Branch branch = mapperUtilForApi.getBranchById( id );
            branch.setAddress( branchPutRequest.getAddress() );
            branch.setName( branchPutRequest.getName() );
            if ( branchPutRequest.isBranchStatus() ) {
                branch.setStatus( BranchStatus.ACTIVE );
            } else {
                branch.setStatus( BranchStatus.DE_ACTIVE );
            }
            return branch;
        } catch ( Exception e ) {
            throw new ResourceNotFoundException( "Could not update branch with id " + id + " because it is not found" );
        }

    }

    public Branch mapFromBranchPatchRequestToBranch( Boolean branchStatus, int id ) {
        Branch branch = mapperUtilForApi.getBranchById( id );
        if ( branchStatus ) {
            branch.setStatus( BranchStatus.ACTIVE );
        } else {
            branch.setStatus( BranchStatus.DE_ACTIVE );
        }
        return branch;
    }

    public List<StudentResponse> mapFromListOfStudentToListOfStudentResponses(List<User> users ) {
        return users.stream().map( this::mapFromStudentToStudentResponse ).collect( Collectors.toList() );
    }

    public StudentResponse mapFromStudentToStudentResponse( User user ) {
        StudentResponse response = new StudentResponse();
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        response.setUserName(user.getUsername());
        response.setTrack(user.getTracks().get(0).getName());
        response.setPassword(user.getPassword());
        response.setRole(user.getRole().getName());
        return response;
    }

    public User mapFromStudentRequestToStudent(StudentRequest studentRequest){
        User user = new User();
        Role role = new Role();
        Track track = new Track();
        List tracks = new ArrayList<>();
       // role.setId();
        role.setName("STUDENT");
        track=mapperUtilForApi.getTrackById(studentRequest.getTrackId());
        tracks.add(track);
        user.setUsername(studentRequest.getUsername());
        user.setRole(role);
        user.setEmail(studentRequest.getEmail());
        user.setPassword("student");
        user.setTracks(tracks);
        return user;
    }
}

