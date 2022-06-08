package eg.gov.iti.jets.api.resource.track;


import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TrackViewResponse {
    private Boolean success;
    private TrackResponse trackResponse;
}


