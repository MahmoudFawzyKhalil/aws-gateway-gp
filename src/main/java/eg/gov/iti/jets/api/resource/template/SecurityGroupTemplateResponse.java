package eg.gov.iti.jets.api.resource.template;



public interface SecurityGroupTemplateResponse {

    Integer getId();
    String getSecurityGroupId();
    String getName();
    String getDescription();
    String getVpcId();
}
