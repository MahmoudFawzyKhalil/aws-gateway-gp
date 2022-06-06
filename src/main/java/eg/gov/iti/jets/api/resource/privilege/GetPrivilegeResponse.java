package eg.gov.iti.jets.api.resource.privilege;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPrivilegeResponse {
    private int id;
    private String name;
}
