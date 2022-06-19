package eg.gov.iti.jets.api.resource.branch;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class BranchResponseList {
    private List<BranchResponse> branchResponsesList = new ArrayList<>();
}