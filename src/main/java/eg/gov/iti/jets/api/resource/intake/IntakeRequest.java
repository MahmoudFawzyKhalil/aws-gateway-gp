package eg.gov.iti.jets.api.resource.intake;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IntakeRequest {
    private int id;
    private String intakeDescription;
    private String intakeName;
    private Integer trainingProgramId;
}
