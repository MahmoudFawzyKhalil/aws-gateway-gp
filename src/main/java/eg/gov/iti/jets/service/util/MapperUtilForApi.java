package eg.gov.iti.jets.service.util;

import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
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


    public TrainingProgram getTrainingProgramById(int id) {
        Optional<TrainingProgram> trainingProgram = trainingProgramDao.findById(id);
        return trainingProgram.orElse( null );
    }

    public Intake getIntackById(int id) {
        Optional<Intake> intake = intakeDao.findById(id);
        System.out.println("##########################################################");
        System.out.println(intake);
        return intake.orElse( null );
    }




}
