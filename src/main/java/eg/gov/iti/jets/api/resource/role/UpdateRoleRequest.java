package eg.gov.iti.jets.api.resource.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleRequest {
    private int id;
    private String name;
    private List<Integer> privileges;
}
