package eg.gov.iti.jets.api.resource.ami;

import eg.gov.iti.jets.api.resource.ami.AmiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmiViewResponse {
    private Boolean success;
    private AmiResponse amiResponse;
}
