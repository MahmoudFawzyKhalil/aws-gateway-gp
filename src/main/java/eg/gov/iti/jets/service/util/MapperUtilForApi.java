package eg.gov.iti.jets.service.util;

import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MapperUtilForApi {
    private final SecurityGroupDao securityGroupDao;
    private final UserDao userDao;
    private final IntakeDao intakeDao;
    private final BranchDao branchDao;
    private final TrainingProgramDao trainingProgramDao;
    private final AwsGateway awsGateway;
    private final KeyPairDao keyPairDao;
    private final TrackDao trackDao;
    private final TemplateConfigurationDao templateConfigurationDao;
    private final RoleDao roleDao;

    public List<SecurityGroup> getSecurityGroups(List<String> ids){
        return  awsGateway.describeSecurityGroupsForIds( ids );
    }

    public List<String> getSecurityGroupsName(List<SecurityGroup> securityGroups){
        List<String> securityGroupsNames = new ArrayList<>();
        for (SecurityGroup securityGroup:securityGroups){
            securityGroupsNames.add( securityGroup.getName() );

        }
        return securityGroupsNames;
    }

    public User getUser( int id){
        Optional<User> byId = userDao.findById( id );
        return byId.orElseThrow(()->new ResourceNotFoundException("User with id " + id + ", is not found!"));
    }


    public Ami getAmiObject( String amiId){
        Optional<Ami> ami = awsGateway.describeAmi( amiId );
        return ami.orElseThrow(()->new ResourceNotFoundException("Ami with id " + amiId + ", is not found!"));
    }


    public Branch getBranchById(int id) {
        Optional<Branch> branch = branchDao.findById(id);
        return branch.orElseThrow(() -> new ResourceNotFoundException("Branch with id " + branch + ", is not found!"));

    }

    public TemplateConfiguration getTemplateConfigurationById( int id) {
        Optional<TemplateConfiguration> templateConfigurationDaoById = templateConfigurationDao.findById( id );
        return templateConfigurationDaoById.orElseThrow(()->new ResourceNotFoundException("Template with id " + id + ", is not found!"));
    }
    public List<TemplateConfiguration> getTemplateConfigurationsByIds( List<Integer> templateConfIds) {
        List<TemplateConfiguration> templateConfigurations = new ArrayList<>();
        for ( Integer templateCongId :
                templateConfIds ) {
            Optional<TemplateConfiguration> templateConfiguration = templateConfigurationDao.findById( templateCongId );
            templateConfiguration.ifPresent( templateConfigurations::add );
        }
        return templateConfigurations;
    }

    public KeyPair getKeyPair( String keyPair , int id) {
        KeyPair example = new KeyPair();
        example.setKeyName( keyPair );
        List<KeyPair> keyPairList = keyPairDao.findAllByExample( example );
        if (keyPairList.isEmpty()){
            KeyPair keyPair1 = awsGateway.createKeyPair( keyPair );
            keyPair1.setCreator( getUser( id ) );
            keyPairDao.save( keyPair1 );
            return keyPair1;
        }
        return keyPairList.get( 0 );
    }

    public List<User> getUsers( List<Integer> studentId ) {
        List<User> listOfUser = new ArrayList<>();
        for ( Integer id : studentId ) {
            listOfUser.add( getUser( id ) );
        }
        return listOfUser;
    }


    public TrainingProgram getTrainingProgramById(int id) {
        Optional<TrainingProgram> trainingProgram = trainingProgramDao.findById(id);
        return trainingProgram.orElseThrow( ()->new ResourceNotFoundException("Could not update TrainingProgram with that id  " + id ) );
    }

    public Intake getIntakeById(int id) {
        Optional<Intake> intake = intakeDao.findById(id);
        return intake.orElseThrow( ()->new ResourceNotFoundException("Intake with id " + id + ", is not found") );
    }


    public List<TrainingProgram> getTrainingProgramList( List<Integer> trainingPrograms ) {
        List<TrainingProgram> trainingProgramList = new ArrayList<>();
        for ( Integer id    :
                trainingPrograms ) {
            Optional<TrainingProgram> trainingProgram = trainingProgramDao.findById( id );
            trainingProgram.ifPresent( trainingProgramList::add );
        }
        return trainingProgramList;
    }

    public List<Intake> getIntakeList( List<Integer> intakeId ) {
        List<Intake> intakes = new ArrayList<>();
        for ( Integer id    :
                intakeId ) {
            Optional<Intake> trainingProgram = intakeDao.findById( id );
            trainingProgram.ifPresent( intakes::add );
        }
        return intakes;
    }



    public Track getTrackById(int id) {
        Optional<Track> track = trackDao.findById(id);
        return track.orElseThrow( ()->new ResourceNotFoundException("Track with id " + id + ", is not found") );

    }
    public Role getRole(String roleName) {

        Optional<Role> role = roleDao.findRoleByName(roleName);
        return role.orElseThrow( ()->new ResourceNotFoundException("Role with name " + roleName + ", is not found") );
    }
}
