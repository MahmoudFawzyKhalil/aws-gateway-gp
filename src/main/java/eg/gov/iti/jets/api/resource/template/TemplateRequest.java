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
    private List<String> securityGroups;
    // TODO: 6/7/2022 Mariam el request hhot feh list of instructor
    private List<Integer> instructorId;
}
