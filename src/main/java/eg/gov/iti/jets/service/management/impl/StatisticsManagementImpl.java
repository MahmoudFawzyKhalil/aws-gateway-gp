package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.statistics.StatisticsResponse;
import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.StatisticsManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StatisticsManagementImpl implements StatisticsManagement {
    private final IntakeDao intakeDao;
    private final TrainingProgramDao trainingProgramDao;
    private final InstanceDao instanceDao;

    private final BranchDao branchDao;
    private final UserDao userDao;
    private final AwsGateway awsGateway;
    @Override
    public StatisticsResponse getStatistics() {
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setNameOfIntakes( getIntakeNames() );
        statisticsResponse.setNameOfTrainingPrograms( getTrainingProgramNames() );
        statisticsResponse.setNumberOfOnInstance( getRunning() );
        statisticsResponse.setNumberOfInstance( getAllInstances() );
        statisticsResponse.setNumberOfBranches( branchDao.countAll() );
        statisticsResponse.setNumberOfUsers( userDao.countAll() );
        statisticsResponse.setNumberOfOffInstance( getStopped() );
        statisticsResponse.setNumberOfPendingInstance( getPending() );
        return statisticsResponse;
    }

    private long getPending() {
        List<Instance> instances = instanceDao.findAll();
        awsGateway.updateInstancesInfoFromAws( instances );
        return instances.stream().filter( instance -> instance.getState().equals( "pending" ) ).count();
    }

    private long getRunning() {
        List<Instance> instances = instanceDao.findAll();
        awsGateway.updateInstancesInfoFromAws( instances );
        return instances.stream().filter( instance -> instance.getState().equals( "running" ) ).count();
    }
    private long getStopped() {
        List<Instance> instances = instanceDao.findAll();
        awsGateway.updateInstancesInfoFromAws( instances );
        long stopping = instances.stream().filter( instance -> instance.getState().equals( "stopping" ) ).count();
        long stopped = instances.stream().filter( instance -> instance.getState().equals( "stopped" ) ).count();
        return stopped+stopping;
    }
    private long getAllInstances() {
        List<Instance> instances = instanceDao.findAll();
        return instances.stream().filter( instance -> !instance.getState().equals( "terminated" ) ).count();
    }

    private List<String> getTrainingProgramNames() {
        List<TrainingProgram> trainingPrograms = trainingProgramDao.findAll();
        return trainingPrograms.stream().map( TrainingProgram::getName ).collect( Collectors.toList() );
    }

    private List<String> getIntakeNames() {
        List<Intake> intakes = intakeDao.findAll();
        return intakes.stream().map( Intake::getName ).collect( Collectors.toList() );
    }
}
