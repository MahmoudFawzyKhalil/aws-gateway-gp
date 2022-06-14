package eg.gov.iti.jets.api.resource.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchPutRequest {
    private int id;
    private String address;
    private String name;
}
