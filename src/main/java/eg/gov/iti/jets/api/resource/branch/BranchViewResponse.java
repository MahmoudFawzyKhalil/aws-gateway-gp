package eg.gov.iti.jets.api.resource.branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BranchViewResponse {
    private Boolean success;
    private BranchResponse branchResponse;
}
