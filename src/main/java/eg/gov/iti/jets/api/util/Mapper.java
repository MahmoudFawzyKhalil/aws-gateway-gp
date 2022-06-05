package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.instance.InstanceResponse;
import eg.gov.iti.jets.api.resource.template.*;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public SubnetResponse mapFromSubnetToSubnetResponse( List<Subnet> subnets ) {
        SubnetResponse subnetResponse = new SubnetResponse();
        subnetResponse.setSubnets( subnets.stream().map( Subnet::getSubnetId ).collect( Collectors.toList() ) );
        return subnetResponse;
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
}
