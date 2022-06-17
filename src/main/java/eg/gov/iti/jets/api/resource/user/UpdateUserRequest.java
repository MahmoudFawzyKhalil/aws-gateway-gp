package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.persistence.entity.Role;
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
    private Role role;
}
