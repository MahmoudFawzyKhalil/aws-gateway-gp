package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.api.resource.ami.AmiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public interface TemplateResponse {
    int getId();
//    private AmiResponse ami;
    String getSubnetId();
    String getInstanceType();
    // TODO: 6/7/2022 SecurityGroup string bs ?? 
//    private List<String> securityGroup;
//    private List<UserTemplateResponse> instructors;
//    private UserTemplateResponse creator;
}