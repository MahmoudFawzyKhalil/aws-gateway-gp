package eg.gov.iti.jets.api.resource.track;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrackPutRequest {
    @NotBlank(message = "must not be empty or null data")
    private String Name;
}
