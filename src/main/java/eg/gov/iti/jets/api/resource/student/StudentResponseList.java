package eg.gov.iti.jets.api.resource.student;

import eg.gov.iti.jets.api.resource.track.TrackResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentResponseList {
    private List<StudentResponse> studentResponsesList = new ArrayList<>();
}