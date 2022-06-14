package eg.gov.iti.jets.api.resource.branch;

import lombok.*;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BranchResponse {
    private int id;
    private String address;
    private String name;
}
