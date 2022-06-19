package eg.gov.iti.jets.api.resource.track;

import eg.gov.iti.jets.api.resource.branch.BranchResponse;
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