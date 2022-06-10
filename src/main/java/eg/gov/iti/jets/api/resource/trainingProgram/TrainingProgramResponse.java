package eg.gov.iti.jets.api.resource.trainingProgram;

import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramResponse {
    private Integer id;
    private String name;
    private String branchName;
    private List<String> intakeNames;
}
