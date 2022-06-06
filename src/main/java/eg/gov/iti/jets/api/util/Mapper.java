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
import eg.gov.iti.jets.api.resource.privilege.PrivilegeRequest;
import eg.gov.iti.jets.api.resource.privilege.PrivilegeResponse;
import eg.gov.iti.jets.api.resource.role.RoleRequest;
import eg.gov.iti.jets.api.resource.role.RoleResponse;
import eg.gov.iti.jets.api.resource.template.TemplateRequest;
import eg.gov.iti.jets.api.resource.template.TemplateResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    public BranchResponse mapFromBranchToBranchResponse( Branch branch ) {
        return null;
    }

    public TrainingProgram mapFromTrainingProgramRequestToTrainingProgram( TrainingProgramRequest trainingProgramRequest ) {
        return null;
    }

    public TrainingProgramResponse mapFromTrainingProgramToTrainingProgramResponse( TrainingProgram trainingProgram ) {
        return null;
    }


    public IntakeResponse mapFromIntakeToIntakeResponse( Intake intake ) {
        return null;
    }

    public Intake mapFromIntakeRequestToIntake( IntakeRequest intakeRequest ) {
        return null;
    }


    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest ) {
        return null;
    }

    public TrackResponse mapFromTrackToTrackResponse( Track track ) {
        return null;
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

    public TemplateConfiguration mapFromTemplateRequestToTemplateConfig( TemplateRequest templateRequest ) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setAmiId( templateRequest.getAmiId() );
        templateConfiguration.setCreator( mapperUtilForApi.getUser( 1 ) );
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

    public PrivilegeResponse mapPrivilegeToPrivilegeResponse(Privilege privilege){
        return new PrivilegeResponse(privilege.getId(), privilege.getName());
    }

    public Privilege mapPrivilegeRequestToPrivilege(PrivilegeRequest privilegeRequest) {
        Privilege privilege = new Privilege();
        privilege.setName(privilegeRequest.getName());
        return privilege;
    }

    public Role mapRoleRequestToRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setPrivileges(
                roleRequest.getPrivileges().stream().map(id ->{
                    Privilege privilege = new Privilege();
                    privilege.setId(id);
                    return privilege;
                }).collect(Collectors.toList())
        );
        return role;
    }

    public RoleResponse mapRoleToRoleResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setName(role.getName());
        roleResponse.setPrivileges(role.getPrivileges().stream().map(
                Privilege::getName
        ).collect(Collectors.toList()));
        return roleResponse;
    }
}
