package eg.gov.iti.jets.api.resource.intake;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IntakeViewResponse {
    private Boolean success;
    private IntakeResponse intakeResponse;
}


