package eg.gov.iti.jets.api.resource.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRolesResponse {
    List<GetRoleResponse> roles;
}
