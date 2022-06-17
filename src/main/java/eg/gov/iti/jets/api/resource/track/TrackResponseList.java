package eg.gov.iti.jets.api.resource.track;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackResponseList {
    private List<TrackResponse> trackResponsesList = new ArrayList<>();
}