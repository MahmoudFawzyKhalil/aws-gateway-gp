package eg.gov.iti.jets.api.resource.user;

import lombok.Data;

@Data
public class CreateUserRequest {
//    private Integer id;
    private String username;
    private String email;
    private String password;
    private RoleType role;
}
