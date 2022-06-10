package eg.gov.iti.jets.api.resource.intake;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntakeResponseList {
    private List<IntakeResponse> intakeResponsesList = new ArrayList<>();
}