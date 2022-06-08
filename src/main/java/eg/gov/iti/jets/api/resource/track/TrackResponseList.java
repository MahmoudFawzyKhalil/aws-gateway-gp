package eg.gov.iti.jets.api.resource.track;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class TrackResponseList {
    private List<TrackResponse> trackResponsesList = new ArrayList<>();
}