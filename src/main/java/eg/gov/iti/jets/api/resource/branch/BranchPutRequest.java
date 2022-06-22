package eg.gov.iti.jets.api.resource.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchPutRequest {
    @NotBlank(message = "must not be empty or null data")
    private String address;
    private String name;
    private boolean branchStatus;
}
