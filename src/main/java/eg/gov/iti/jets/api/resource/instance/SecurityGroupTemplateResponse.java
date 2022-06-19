package eg.gov.iti.jets.api.resource.instance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGroupTemplateResponse {

    Integer id;
    String securityGroupId;
    String name;
    String description;
    String vpcId;

}
