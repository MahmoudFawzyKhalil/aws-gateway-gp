package eg.gov.iti.jets.api.resource.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BranchRequest {
    private int id;
    private String address;
    private String name;
}
