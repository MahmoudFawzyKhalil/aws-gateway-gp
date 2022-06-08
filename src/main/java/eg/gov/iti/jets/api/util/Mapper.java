package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.ami.AmiResponse;
import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.instance.InstanceRequest;
import eg.gov.iti.jets.api.resource.instance.InstanceResponse;
import eg.gov.iti.jets.api.resource.instanceType.InstanceTypeObjectResponse;
import eg.gov.iti.jets.api.resource.instanceType.InstanceTypeResponse;
import eg.gov.iti.jets.api.resource.securityGroup.SecurityGroupResponse;
import eg.gov.iti.jets.api.resource.subnet.SubnetObjectResponse;
import eg.gov.iti.jets.api.resource.subnet.SubnetResponse;
import eg.gov.iti.jets.api.resource.template.*;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.privilege.AddPrivilegeRequest;
import eg.gov.iti.jets.api.resource.privilege.GetPrivilegeResponse;
import eg.gov.iti.jets.api.resource.role.PrivilegeType;
import eg.gov.iti.jets.api.resource.role.AddRoleRequest;
import eg.gov.iti.jets.api.resource.role.GetRoleResponse;
import eg.gov.iti.jets.api.resource.role.UpdateRoleRequest;
import eg.gov.iti.jets.api.resource.template.TemplateRequest;
import eg.gov.iti.jets.api.resource.template.TemplateResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramPutRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import eg.gov.iti.jets.api.resource.user.UserRequest;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.persistence.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Mapper {
    @Autowired
    private MapperUtilForApi mapperUtilForApi;


    public Branch mapFromBranchRequestToBranch( BranchRequest branchRequest ) {
        Branch branch = new Branch();
        branch.setAddress(branchRequest.getAddress());
        branch.setName(branchRequest.getName());
        branch.setId(branchRequest.getId());
        return branch;
    }

    public BranchResponse mapFromBranchToBranchResponse( Branch branch ) {
        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setAddress(branch.getAddress());
        branchResponse.setName(branch.getName());
        return branchResponse;
    }

    public List<BranchResponse> mapFromListOfBranchToListOfBranchResponses(List<Branch> branches){
        List<BranchResponse> branchResponses=
                branches.stream().map(e -> this.mapFromBranchToBranchResponse(e)).collect(Collectors.toList());
        return branchResponses;
    }

    public TrainingProgram mapFromTrainingProgramRequestToTrainingProgram( TrainingProgramRequest trainingProgramRequest ) {
        TrainingProgram trainingProgram = new TrainingProgram();
        Branch branch = mapperUtilForApi.getBranchById(trainingProgramRequest.getBranchId());
        trainingProgram.setBranch(branch);
        trainingProgram.setName(trainingProgramRequest.getName());
        return trainingProgram;
    }

    public TrainingProgramResponse mapFromTrainingProgramToTrainingProgramResponse( TrainingProgram trainingProgram ) {
        TrainingProgramResponse trainingProgramResponse =  new TrainingProgramResponse();
       trainingProgramResponse.setBranchId(trainingProgram.getBranch().getId());
        trainingProgramResponse.setName(trainingProgram.getName());
        trainingProgramResponse.setId(trainingProgram.getId());
        return trainingProgramResponse;
    }


    public IntakeResponse mapFromIntakeToIntakeResponse( Intake intake ) {
        IntakeResponse intakeResponse =  new IntakeResponse();
        intakeResponse.setTrainingProgramId(intake.getTrainingProgram().getId());
        intakeResponse.setIntakeName(intake.getName());
        intakeResponse.setIntakeDescription(intake.getDescription());
        return intakeResponse;
    }




    public Intake mapFromIntakeRequestToIntake( IntakeRequest intakeRequest ) {
        Intake intake = new Intake();
        TrainingProgram trainingProgram = mapperUtilForApi.getTrainingProgramById(intakeRequest.getTrainingProgramId());
        intake.setTrainingProgram(trainingProgram);
        intake.setId(intakeRequest.getId());
        intake.setDescription(intakeRequest.getIntakeDescription());
        intake.setName(intakeRequest.getIntakeName());
        return intake;
    }


    public List<IntakeResponse> mapFromListOfIntakesToListOfIntakeResponses(List<Intake> intakes){
        List<IntakeResponse> intakeResponses =
                intakes.stream().map(e -> this.mapFromIntakeToIntakeResponse(e)).collect(Collectors.toList());
        return intakeResponses;
    }


    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest ) {
        Track track = new Track();
        Intake intake = mapperUtilForApi.getIntackById(trackRequest.getIntakeId());
        track.setIntake(intake);
        track.setId(trackRequest.getId());
        track.setName(trackRequest.getName());
        return track;
    }


    public TrackResponse mapFromTrackToTrackResponse(Track track) {
        TrackResponse trackResponse =  new TrackResponse();
        trackResponse.setName(track.getName());
        trackResponse.setIntakeId(track.getIntake().getId());
        return trackResponse;
    }

    public List<TrackResponse> mapFromListOfTracksToListOfTrackResponses(List<Track> tracks){
        List<TrackResponse> trackResponses =
                tracks.stream().map(e -> this.mapFromTrackToTrackResponse(e)).collect(Collectors.toList());
        return trackResponses;
    }

    public Instance mapFromInstanceRequestToInstance( IntakeRequest intakeRequest ) {
        return null;
    }

    public InstanceResponse mapFromInstanceToInstanceResponse( Optional<Instance> instance ) {
        InstanceResponse instanceResponse = new InstanceResponse();
        instanceResponse.setSuccess( instance.isPresent() );
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
                                    ,subnetResponse.getCidrBlock(),subnetResponse.getMapPublicIpOnLaunch());
            list.add( subnetResponse1 );
        }
        SubnetObjectResponse subnetObjectResponse = new SubnetObjectResponse(list);

        return subnetObjectResponse;
    }

    public TemplateConfiguration mapFromTemplateRequestToTemplateConfig( TemplateRequest templateRequest , int id) {
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
        templateResponse.setSecurityGroup( mapperUtilForApi.getSecurityGroupsName( template.getSecurityGroups() )  );
        templateResponse.setAmi( mapFromAmiToAmiResponse( mapperUtilForApi.getAmiObject( template.getAmiId() ) ) );
        return templateResponse;
    }

    public AmiResponse mapFromAmiToAmiResponse( Ami ami ){
        AmiResponse amiResponse = new AmiResponse( ami.getImageId(), ami.getImageOwnerAlias(), ami.getArchitecture(), ami.getImageName(), ami.getDescription(), ami.getPlatform() );
        return amiResponse;
    }

    public InstanceTypeObjectResponse mapFromInstanceTypeToObjectResponse(List<String> types){
        List<InstanceTypeResponse> list = new ArrayList<>();
        for ( String type :
                types ) {
            InstanceTypeResponse instanceType = new InstanceTypeResponse( type );
            list.add( instanceType );
        }

        InstanceTypeObjectResponse instanceTypeObjectResponse = new InstanceTypeObjectResponse(list);
        return instanceTypeObjectResponse;
    }

    public GetPrivilegeResponse privilegeToGetPrivilegeResponse(Privilege privilege){
        return new GetPrivilegeResponse(privilege.getId(), privilege.getName().name());
    }

    public Privilege addPrivilegeRequestToPrivilege(AddPrivilegeRequest addPrivilegeRequest) {
        Privilege privilege = new Privilege();
        privilege.setName(
                PrivilegeName.valueOf(addPrivilegeRequest.getName())
        );
        return privilege;
    }

    public Role addRoleRequestToRole(AddRoleRequest addRoleRequest) {
        Role role = new Role();
        role.setName(addRoleRequest.getName());
        role.setPrivileges(
                addRoleRequest.getPrivileges().stream().map(id ->{
                    Privilege privilege = new Privilege();
                    privilege.setId(id);
                    return privilege;
                }).collect(Collectors.toList())
        );
        return role;
    }

     public GetRoleResponse roleToGetRoleResponse(Role role) {
            GetRoleResponse getRoleResponse = new GetRoleResponse();
            getRoleResponse.setName(role.getName());
            getRoleResponse.setPrivileges(role.getPrivileges().stream().map(
                    privilege -> {
                        PrivilegeType privilegeRoleType = new PrivilegeType();
                        privilegeRoleType.setId(privilege.getId());
                        privilegeRoleType.setName(privilege.getName().name());
                        return privilegeRoleType;
                    }
            ).collect(Collectors.toList()));
            return getRoleResponse;
    }

    public Role updateRoleRequestToRole(UpdateRoleRequest updateRoleRequest) {
        Role role = new Role();
        role.setId(updateRoleRequest.getId());
        role.setName(updateRoleRequest.getName());
        role.setPrivileges(
                updateRoleRequest.getPrivileges().stream().map(
                        privilegeId-> {
                            Privilege privilege = new Privilege();
                            privilege.setId(privilegeId);
                            return privilege;
                        }
        ).collect(Collectors.toList()));
        return role;
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
        response.setPrivileges(user.getRole().getPrivileges().stream().map(privilege -> {return privilege.getName().name();}).collect(Collectors.toList()));
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



    public TrainingProgram mapFromTrainingProgramPutRequestToTrainingProgram( TrainingProgramPutRequest trainingProgramPutRequest ) {
        TrainingProgram trainingProgram = new TrainingProgram();
        Branch branch = mapperUtilForApi.getBranchById(trainingProgramPutRequest.getBranchId());
        trainingProgram.setBranch(branch);
        trainingProgram.setName(trainingProgramPutRequest.getName());
        trainingProgram.setId(trainingProgramPutRequest.getId());
        return trainingProgram;
    }
}
