package eg.gov.iti.jets.api.resource.trainingProgram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramPutRequest {
    private Integer id;
    private String name;
    private Integer branchId;
    private List<Integer> intakeIds;
}
