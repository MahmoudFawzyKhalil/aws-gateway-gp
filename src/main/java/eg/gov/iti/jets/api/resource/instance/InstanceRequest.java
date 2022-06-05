package eg.gov.iti.jets.api.resource.instance;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstanceRequest {
    private int templateId;
    private String keyPair;
    private String instanceName;
}
