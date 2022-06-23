package eg.gov.iti.jets.api.resource.trainingProgram;

import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramRequest {
    @NotBlank(message = "must not be empty or null")
    private String name;
    @NotBlank(message = "must not be empty or null")
    private Integer branchId;
}
