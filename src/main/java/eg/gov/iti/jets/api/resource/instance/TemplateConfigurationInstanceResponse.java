package eg.gov.iti.jets.api.resource.instance;

import eg.gov.iti.jets.api.resource.template.SecurityGroupTemplateResponse;
import eg.gov.iti.jets.api.resource.template.UserTemplateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TemplateConfigurationInstanceResponse {

    Integer id;
    String amiId;
    String subnetId;
    String instanceType;
    List<SecurityGroupTemplateResponse> securityGroups;
    UserTemplateResponse creator;
}
