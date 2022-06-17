package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.resource.ami.AmiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TemplateResponse {
    private int id;
    private AmiResponse ami;
    private String subnetId;
    private String instanceType;

    // TODO: 6/7/2022 SecurityGroup string bs ?? 
    private List<String> securityGroup;
    private List<UserTemplateResponse> instructors;
    private UserTemplateResponse creator; 
}