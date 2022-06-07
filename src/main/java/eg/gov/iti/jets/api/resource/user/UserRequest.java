package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.persistence.entity.Role;
import lombok.Data;

@Data
public class UserRequest {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
