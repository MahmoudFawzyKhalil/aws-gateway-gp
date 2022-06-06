package eg.gov.iti.jets.api.resource.privilege;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPrivilegesResponse {
    List<PrivilegeTypeResponse> privileges;
}
