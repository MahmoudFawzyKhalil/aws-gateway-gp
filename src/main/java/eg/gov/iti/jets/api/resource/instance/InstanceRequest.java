package eg.gov.iti.jets.api.resource.instance;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstanceRequest {
    private Integer templateId;
    private List<Integer> studentIds;
    private String keyPair;
    private String instanceName;
    private Long timeToLiveInMinutes;
}
