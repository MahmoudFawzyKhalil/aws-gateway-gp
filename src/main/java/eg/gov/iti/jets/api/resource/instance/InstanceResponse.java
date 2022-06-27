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
    private String lastStartedDateTime;
    private String keyPair;
    private Long timeToLiveInMinutes;
    private UserInstanceResponse creator;
    private UserInstanceResponse instanceUser;
    private TemplateConfigurationInstanceResponse templateConfiguration;
}
