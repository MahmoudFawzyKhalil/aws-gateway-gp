package eg.gov.iti.jets.api.resource.template;

import java.util.List;
public interface TemplateResponse {
    Integer getId();
    String getAmiId();
    String getSubnetId();
    String getInstanceType();
    List<SecurityGroupTemplateResponse> getSecurityGroups();
    UserTemplateResponse getCreator();
}