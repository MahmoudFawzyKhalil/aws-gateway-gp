package eg.gov.iti.jets.api.resource.intake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntakePutRequest {
    @NotBlank(message = "must not be empty or null")
    private String intakeDescription;
    @NotBlank(message = "must not be empty or null")
    private String intakeName;
}
