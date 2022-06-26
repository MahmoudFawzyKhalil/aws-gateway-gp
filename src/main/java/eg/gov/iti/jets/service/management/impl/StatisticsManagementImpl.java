package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.statistics.StatisticsResponse;
import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.management.StatisticsManagement;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class StatisticsManagementImpl implements StatisticsManagement {
    private final IntakeDao intakeDao;
    private final TrainingProgramDao trainingProgramDao;
    private final InstanceDao instanceDao;

    private final BranchDao branchDao;
    private final UserDao userDao;
    @Override
    public StatisticsResponse getStatistics() {
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        List<String> intakeNames = getIntakeNames();
        statisticsResponse.setNameOfIntakes( intakeNames );

        List<String> trainingProgramNames = getTrainingProgramNames();
        statisticsResponse.setNameOfTrainingPrograms( trainingProgramNames );

        Long running = instanceDao.countAllByState( "running" );
        statisticsResponse.setNumberOfOnInstance( running );

        Long stopped = instanceDao.countAllByState( "stopped" );
        Long stopping = instanceDao.countAllByState( "stopping" );
        statisticsResponse.setNumberOfOffInstance( stopped + stopping );

        Long numberOfAllInstances = instanceDao.countAllByStateNot( "terminated" );
        statisticsResponse.setNumberOfInstance( numberOfAllInstances );

        Long branchCount = branchDao.countAll();
        statisticsResponse.setNumberOfBranches( branchCount );

        Long countAllUser = userDao.countAll();
        statisticsResponse.setNumberOfUsers( countAllUser );

        return statisticsResponse;
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
