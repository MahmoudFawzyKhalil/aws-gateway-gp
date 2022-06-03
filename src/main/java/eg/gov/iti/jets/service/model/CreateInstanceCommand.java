package eg.gov.iti.jets.service.model;

import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import lombok.*;


@Getter
@AllArgsConstructor
public class CreateInstanceCommand {
    private KeyPair keyPair;
    private String amiId;
    private String instanceType;
    private String subnetId;
    private String tagName;

}
