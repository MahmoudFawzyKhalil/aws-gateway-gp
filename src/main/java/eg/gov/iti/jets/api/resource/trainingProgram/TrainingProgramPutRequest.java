package eg.gov.iti.jets.api.resource.trainingProgram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramPutRequest {
    private Integer id;
    private String name;
    private Integer branchId;

}
