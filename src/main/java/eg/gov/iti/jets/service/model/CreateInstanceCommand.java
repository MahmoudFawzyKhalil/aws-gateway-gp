package eg.gov.iti.jets.service.model;

import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import lombok.*;
import software.amazon.awssdk.services.ec2.model.InstanceType;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class CreateInstanceCommand {
    private KeyPair keyPair;
    private String amiId;
    private String instanceType;
    private String subnetId;
    private String instanceName;
    private List<String> securityGroupNames;
    private List<String> securityGroupIds;
}
