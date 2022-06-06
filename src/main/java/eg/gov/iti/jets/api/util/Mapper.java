package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.instance.InstanceResponse;
import eg.gov.iti.jets.api.resource.instance.SecurityGroupResponse;
import eg.gov.iti.jets.api.resource.instance.SubnetResponse;
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
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.util.MapperFromIdToSecurityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Mapper {
    @Autowired
    private MapperFromIdToSecurityGroup mapperFromIdToSecurityGroup;

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

    public SubnetResponse mapFromSubnetToSubnetResponse( List<Subnet> subnets ) {
        SubnetResponse subnetResponse = new SubnetResponse();
        subnetResponse.setSubnets( subnets.stream().map( Subnet::getSubnetId ).collect( Collectors.toList() ) );
        return subnetResponse;
    }

    public TemplateConfiguration mapFromTemplateRequestToTemplateConfig( TemplateRequest templateRequest ) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setAmiId( templateRequest.getAmiId() );
        templateConfiguration.setCreator( mapperFromIdToSecurityGroup.getUser( 1 ) );
        templateConfiguration.setSubnetId( templateRequest.getSubnetId() );
        templateConfiguration.setInstanceType( templateRequest.getInstanceType() );
        templateConfiguration.setInstructors( null );
        templateConfiguration.setSecurityGroups( mapperFromIdToSecurityGroup.getSecurityGroups( templateRequest.getSecurityGroups() ) );
        return templateConfiguration;
    }

    public SecurityGroupResponse mapFromSecurityGroupToSecurityGroupResponse( SecurityGroup securityGroup ) {
        SecurityGroupResponse securityGroupResponse = new SecurityGroupResponse();
        securityGroupResponse.setId( securityGroup.getId() );
        securityGroupResponse.setName( securityGroup.getName() );
        return securityGroupResponse;

    }


    public TemplateResponse mapFromTemplateToTemplateResponse( TemplateConfiguration template ) {

        return new TemplateResponse( template.getId() );
    }

    public GetPrivilegeResponse privilegeToGetPrivilegeResponse(Privilege privilege){
        return new GetPrivilegeResponse(privilege.getId(), privilege.getName());
    }

    public Privilege addPrivilegeRequestToPrivilege(AddPrivilegeRequest addPrivilegeRequest) {
        Privilege privilege = new Privilege();
        privilege.setName(addPrivilegeRequest.getName());
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
                    privilegeRoleType.setName(privilege.getName());
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
}
