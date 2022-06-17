package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;

import java.util.List;

public interface TrainingProgramManagement {

    public Boolean createTrainingProgram( TrainingProgram trainingProgram);

    public TrainingProgram updateTrainingProgram( TrainingProgram trainingProgram );

    public Boolean deleteTrainingProgram ( int id);

    public List<TrainingProgram> getAllTrainingPrograms();

    public TrainingProgram getTrainingProgramById( int id );

    List<Intake> getIntakeByProgramId( int programId );
}
