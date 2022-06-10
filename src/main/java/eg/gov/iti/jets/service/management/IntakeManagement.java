package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Intake;
import java.util.List;
import java.util.Optional;

public interface IntakeManagement {
    Intake createIntake(Intake intake );

    Intake updateIntake( Intake intake );

    List<Intake> getAllIntakes();

    Optional<Intake> getIntakeById(int id);

    List<Intake> getIntakeByProgramId( int programId );
}
