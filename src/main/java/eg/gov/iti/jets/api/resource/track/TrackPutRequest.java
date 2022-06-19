package eg.gov.iti.jets.api.resource.track;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrackPutRequest {
    private int id;
    private String Name;
    private int IntakeId;
}
