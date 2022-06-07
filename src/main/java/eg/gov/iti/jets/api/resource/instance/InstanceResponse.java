package eg.gov.iti.jets.api.resource.instance;

import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstanceResponse {
    private int id;
    private String name;
    private String amiId;
    private String instanceId;
    private String state;
    private String publicIp;
    private String publicDnsName;
    private String instanceType;
    private String subnetId;
    private String vpcId;
    private String platform;
    private String decryptedPassword;
    private String username;
    private String creationDateTime;
    private KeyPairInstanceResponse keyPair;
    private UserInstanceResponse creator;
    private List<UserInstanceResponse> instanceUsers;
    private TemplateConfigurationInstanceResponse templateConfiguration;
}
