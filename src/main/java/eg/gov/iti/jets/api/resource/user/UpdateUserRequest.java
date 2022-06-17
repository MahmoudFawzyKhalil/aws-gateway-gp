package eg.gov.iti.jets.api.resource.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private RoleType role;
    private Integer managerId;

}
