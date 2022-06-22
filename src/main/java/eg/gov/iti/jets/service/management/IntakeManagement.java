package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;

import java.util.List;
import java.util.Optional;

public interface IntakeManagement {
    Intake createIntake(Intake intake );

    Intake updateIntake( Intake intake );

    List<Intake> getAllIntakes();

    Intake getIntakeById(int id);



    List<Track> getTrackByIntakeId( int intakeId );
}
