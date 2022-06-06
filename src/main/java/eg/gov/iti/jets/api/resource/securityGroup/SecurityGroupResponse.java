package eg.gov.iti.jets.api.resource.securityGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGroupResponse {

    private Integer id;
    private String securityGroupId;
    private String name;
    private String description;
    private String vpcId;
}
