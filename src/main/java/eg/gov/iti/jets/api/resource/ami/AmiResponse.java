package eg.gov.iti.jets.api.resource.ami;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmiResponse {

    private String imageId;
    private String imageOwnerAlias;
    private String architecture;
    private String imageName;
    private String description;
    private String platform;
}
