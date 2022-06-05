package eg.gov.iti.jets.api.resource.instance;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstanceRequest {

    // template
    private int templateId;
    private int studentId;
    private String keyPair;
    private String instanceName;
}
