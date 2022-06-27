package eg.gov.iti.jets.api.resource.instance;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstanceRequest {
    @NotNull(message = "must not be null or empty")
    private Integer templateId;
    private List<Integer> studentIds;
    private String keyPair;
    private String instanceName;
    private Long timeToLiveInMinutes;
}
