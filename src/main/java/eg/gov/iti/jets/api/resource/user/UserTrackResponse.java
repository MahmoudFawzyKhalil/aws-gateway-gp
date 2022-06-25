package eg.gov.iti.jets.api.resource.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserTrackResponse {
    private Integer id;
    private String trackName;
}
