package eg.gov.iti.jets.api.resource.intake;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class IntakeResponseList {
    private List<IntakeResponse> intakeResponsesList = new ArrayList<>();
}