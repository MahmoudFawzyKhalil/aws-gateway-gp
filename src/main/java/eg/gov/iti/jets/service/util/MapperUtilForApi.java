package eg.gov.iti.jets.service.util;

import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MapperUtilForApi {

    @Autowired
    private SecurityGroupDao securityGroupDao;
    @Autowired
    UserDao userDao;

    @Autowired
    IntakeDao intakeDao;

    @Autowired
    private BranchDao branchDao;

    @Autowired
    private TrainingProgramDao trainingProgramDao;
    @Autowired
    AwsGateway awsGateway;

    @Autowired
    KeyPairDao keyPairDao;
    @Autowired
    TrackDao trackDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    TemplateConfigurationDao templateConfigurationDao;

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
        return byId.orElse( null );
    }


    public Ami getAmiObject( String amiId){
        Optional<Ami> ami = awsGateway.describeAmi( amiId );
        return ami.orElse( null );
    }


    public Branch getBranchById(int id) {
        Optional<Branch> branch = branchDao.findById(id);
        return branch.orElse( null );
    }

    public TemplateConfiguration getTemplateConfigurationById( int id) {
        Optional<TemplateConfiguration> templateConfigurationDaoById = templateConfigurationDao.findById( id );
        return templateConfigurationDaoById.orElse( null );
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

return null;
    }
}
