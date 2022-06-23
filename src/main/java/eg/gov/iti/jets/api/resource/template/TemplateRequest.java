package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest {
    @NotBlank(message = "must not be null or empty")
    private String amiId;
    @NotBlank(message = "must not be null or empty")
    private String subnetId;
    @NotBlank(message = "must not be null or empty")
    private String instanceType;
    
    private List<String> securityGroups;
    private List<Integer> instructorIds;
}
