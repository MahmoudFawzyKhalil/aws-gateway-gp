package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.ami.AmiResponse;
import eg.gov.iti.jets.api.resource.branch.BranchPutRequest;
import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.instance.InstanceRequest;
import eg.gov.iti.jets.api.resource.instance.InstanceResponse;
import eg.gov.iti.jets.api.resource.instanceType.InstanceTypeObjectResponse;
import eg.gov.iti.jets.api.resource.instanceType.InstanceTypeResponse;
import eg.gov.iti.jets.api.resource.role.*;
import eg.gov.iti.jets.api.resource.securityGroup.SecurityGroupResponse;
import eg.gov.iti.jets.api.resource.subnet.SubnetObjectResponse;
import eg.gov.iti.jets.api.resource.subnet.SubnetResponse;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.privilege.AddPrivilegeRequest;
import eg.gov.iti.jets.api.resource.privilege.GetPrivilegeResponse;
import eg.gov.iti.jets.api.resource.template.TemplateRequest;
import eg.gov.iti.jets.api.resource.template.TemplateResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramPutRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.api.resource.user.UpdateUserRequest;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.enums.BranchStatus;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import eg.gov.iti.jets.api.resource.user.CreateUserRequest;
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
        if ( trainingProgramRequest != null || trainingProgramRequest.getIntakeIds().isEmpty() ) {
            trainingProgram.setIntakes( mapperUtilForApi.getIntakeList( trainingProgramRequest.getIntakeIds() ) );
        }
        return trainingProgram;
    }

    public TrainingProgramResponse mapFromTrainingProgramToTrainingProgramResponse( TrainingProgram trainingProgram ) {
        TrainingProgramResponse trainingProgramResponse = new TrainingProgramResponse();
        trainingProgramResponse.setBranchName( trainingProgram.getBranch().getName() );
        trainingProgramResponse.setName( trainingProgram.getName() );
        trainingProgramResponse.setId( trainingProgram.getId() );
        trainingProgramResponse.setIntakeNames( trainingProgram.getIntakes().stream().map( Intake::getName ).collect( Collectors.toList() ) );
        return trainingProgramResponse;
    }


    public IntakeResponse mapFromIntakeToIntakeResponse( Intake intake ) {
        IntakeResponse intakeResponse = new IntakeResponse();
        intakeResponse.setTrainingProgramId( intake.getTrainingProgram().getId() );
        intakeResponse.setIntakeName( intake.getName() );
        intakeResponse.setIntakeDescription( intake.getDescription() );
        return intakeResponse;
    }


    public Intake mapFromIntakeRequestToIntake( IntakeRequest intakeRequest ) {
        Intake intake = new Intake();
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById( intakeRequest.getTrainingProgramId() );
        intake.setTrainingProgram( trainingProgram );
        intake.setId( intakeRequest.getId() );
        intake.setDescription( intakeRequest.getIntakeDescription() );
        intake.setName( intakeRequest.getIntakeName() );
        return intake;
    }


    public List<IntakeResponse> mapFromListOfIntakesToListOfIntakeResponses( List<Intake> intakes ) {
        List<IntakeResponse> intakeResponses =
                intakes.stream().map( e -> this.mapFromIntakeToIntakeResponse( e ) ).collect( Collectors.toList() );
        return intakeResponses;
    }


    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest ) {
        Track track = new Track();
        Intake intake = mapperUtilForApi.getIntackById( trackRequest.getIntakeId() );
        track.setIntake( intake );
        track.setId( trackRequest.getId() );
        track.setName( trackRequest.getName() );
        return track;
    }


    public TrackResponse mapFromTrackToTrackResponse( Track track ) {
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setName( track.getName() );
        trackResponse.setIntakeId( track.getIntake().getId() );
        return trackResponse;
    }

    public List<TrackResponse> mapFromListOfTracksToListOfTrackResponses( List<Track> tracks ) {
        List<TrackResponse> trackResponses =
                tracks.stream().map( e -> this.mapFromTrackToTrackResponse( e ) ).collect( Collectors.toList() );
        return trackResponses;
    }

    public Instance mapFromInstanceRequestToInstance( IntakeRequest intakeRequest ) {
        return null;
    }

    public InstanceResponse mapFromInstanceToInstanceResponse( Instance instance ) {
        InstanceResponse instanceResponse = new InstanceResponse();
        instanceResponse.setInstanceId( instance.getInstanceId() );
        instanceResponse.setInstanceType( instance.getInstanceType() );
//        instanceResponse.setInstanceUsers( instance.getInstanceUsers() );
//        instanceResponse.setCreator( instance.getCreator() );
        instanceResponse.setId( instance.getId() );
        instanceResponse.setName( instance.getName() );
        instanceResponse.setVpcId( instance.getVpcId() );
        instanceResponse.setSubnetId( instance.getSubnetId() );
        instanceResponse.setAmiId( instance.getAmiId() );
//        instanceResponse.setKeyPair( instance.getKeyPair() );
        instanceResponse.setPlatform( instance.getPlatform() );
        instanceResponse.setCreationDateTime( instance.getCreationDateTime().toString() );
        instanceResponse.setDecryptedPassword( instance.getDecryptedPassword() );
//        instanceResponse.setTemplateConfiguration( instance.getTemplateConfiguration() );
        instanceResponse.setPublicDnsName( instance.getPublicDnsName() );
        instanceResponse.setUsername( instance.getUsername() );
        instanceResponse.setState( instance.getState() );
        return instanceResponse;
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

    public TemplateConfiguration mapFromTemplateRequestToTemplateConfig( TemplateRequest templateRequest, int id ) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setAmiId( templateRequest.getAmiId() );
        templateConfiguration.setCreator( mapperUtilForApi.getUser( id ) );
        templateConfiguration.setSubnetId( templateRequest.getSubnetId() );
        templateConfiguration.setInstanceType( templateRequest.getInstanceType() );
        templateConfiguration.setInstructors( null );
        templateConfiguration.setSecurityGroups( mapperUtilForApi.getSecurityGroups( templateRequest.getSecurityGroups() ) );
        return templateConfiguration;
    }

    public SecurityGroupResponse mapFromSecurityGroupToSecurityGroupResponse( SecurityGroup securityGroup ) {
        SecurityGroupResponse securityGroupResponse = new SecurityGroupResponse();
        securityGroupResponse.setId( securityGroup.getId() );
        securityGroupResponse.setName( securityGroup.getName() );
        securityGroupResponse.setSecurityGroupId( securityGroup.getSecurityGroupId() );
        securityGroupResponse.setDescription( securityGroup.getDescription() );
        securityGroupResponse.setVpcId( securityGroup.getVpcId() );
        return securityGroupResponse;

    }


    public TemplateResponse mapFromTemplateToTemplateResponse( TemplateConfiguration template ) {
        TemplateResponse templateResponse = new TemplateResponse();
        templateResponse.setId( template.getId() );
        templateResponse.setInstanceType( template.getInstanceType() );
        templateResponse.setSubnetId( template.getSubnetId() );
        templateResponse.setSecurityGroup( mapperUtilForApi.getSecurityGroupsName( template.getSecurityGroups() ) );
        templateResponse.setAmi( mapFromAmiToAmiResponse( mapperUtilForApi.getAmiObject( template.getAmiId() ) ) );
        return templateResponse;
    }

    public AmiResponse mapFromAmiToAmiResponse( Ami ami ) {
        AmiResponse amiResponse = new AmiResponse( ami.getImageId(), ami.getImageOwnerAlias(), ami.getArchitecture(), ami.getImageName(), ami.getDescription(), ami.getPlatform() );
        return amiResponse;
    }

    public InstanceTypeObjectResponse mapFromInstanceTypeToObjectResponse( List<String> types ) {
        List<InstanceTypeResponse> list = new ArrayList<>();
        for ( String type :
                types ) {
            InstanceTypeResponse instanceType = new InstanceTypeResponse( type );
            list.add( instanceType );
        }

        InstanceTypeObjectResponse instanceTypeObjectResponse = new InstanceTypeObjectResponse( list );
        return instanceTypeObjectResponse;
    }

    public Instance mapFromInstanceReqToInstance( InstanceRequest instanceRequest, int creatorId ) {
        Instance instance = new Instance();
        instance.setTemplateConfiguration( mapperUtilForApi.getTemplateConfigurationById( instanceRequest.getTemplateId() ) );
        instance.setKeyPair( mapperUtilForApi.getKeyPair( instanceRequest.getKeyPair(), creatorId ) );
        instance.setName( instanceRequest.getInstanceName() );
        instance.setInstanceUsers( mapperUtilForApi.getUsers( instanceRequest.getStudentIds() ) );
        instance.setCreator( mapperUtilForApi.getUser( creatorId ) );
        return instance;
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

    public Role roleRequestToRole(RoleRequest roleRequest ) {
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

    public GetRoleResponse roleToGetRoleResponse(Role role ) {
        GetRoleResponse getRoleResponse = new GetRoleResponse();
        getRoleResponse.setId(role.getId());
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

    public RoleResponse roleToRoleResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        roleResponse.setPrivileges(
                role.getPrivileges().stream().map(Privilege::getId).collect(Collectors.toList())
        );
        return roleResponse;
    }

    public User createUserRequestToUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        User manager = new User();
        manager.setId(userRequest.getManagerId());

        user.setManager(manager);

        Role role = new Role();
        role.setId( userRequest.getRole().getId());
        role.setName(userRequest.getRole().getName());

        user.setRole(role);
        return user;
    }

    public User updateUserRequestToUser(int id , UpdateUserRequest updateUserRequest) {
        User user = new User();
        user.setId(id);
        user.setEmail(updateUserRequest.getEmail());
        user.setUsername(updateUserRequest.getUsername());
        user.setPassword(updateUserRequest.getPassword());

        User manager = new User();
        manager.setId(updateUserRequest.getManagerId());

        user.setManager(manager);

        Role role = new Role();
        role.setId( updateUserRequest.getRole().getId());
        role.setName(updateUserRequest.getRole().getName());

        user.setRole(role);
        return user;
    }

    public UserResponse mapFromUserToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().getName());
        response.setPassword(user.getPassword());
        response.setPrivileges(user.getRole().getPrivileges().stream().map(privilege -> privilege.getName().name()).collect(Collectors.toList()));
        return response;
    }

    public List<UserResponse> mapFromListOfUsersToListOfUserResponses(List<User> users){
        return users.stream().map(this::mapFromUserToUserResponse).collect(Collectors.toList());
    }


    public TrainingProgram mapFromTrainingProgramPutRequestToTrainingProgram( TrainingProgramPutRequest trainingProgramPutRequest , int id ) {
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById( id );
        Branch branch = mapperUtilForApi.getBranchById( trainingProgramPutRequest.getBranchId() );
        trainingProgram.setBranch( branch );
        trainingProgram.setName( trainingProgramPutRequest.getName() );
        trainingProgram.setId( id );
//        if ( trainingProgramPutRequest != null || !trainingProgramPutRequest.getIntakeIds().isEmpty() ) {
//            trainingProgram.setIntakes( mapperUtilForApi.getIntakeList( trainingProgramPutRequest.getIntakeIds() ) );
//        }
        return trainingProgram;
    }

    public Branch mapFromBranchPutRequestToBranch( BranchPutRequest branchPutRequest, int id ) {
        Branch branch = mapperUtilForApi.getBranchById( id );
        branch.setAddress( branchPutRequest.getAddress() );
        branch.setName( branchPutRequest.getName() );
        if ( branchPutRequest.isBranchStatus() ) {
            branch.setStatus( BranchStatus.ACTIVE );
        } else {
            branch.setStatus( BranchStatus.DE_ACTIVE );
        }
        return branch;
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
}
