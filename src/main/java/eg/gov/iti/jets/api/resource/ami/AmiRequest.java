package eg.gov.iti.jets.api.resource.ami;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmiRequest {
    @NotBlank(message = "must not be empty or null")
    private String amiId;
}
