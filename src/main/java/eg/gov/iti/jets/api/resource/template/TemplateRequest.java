package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest {
    private String amiId;
    private String subnetId;
    private String instanceType;
    private List<Integer> securityGroups;
}
