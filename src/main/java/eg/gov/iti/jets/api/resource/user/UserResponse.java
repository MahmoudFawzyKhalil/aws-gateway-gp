package eg.gov.iti.jets.api.resource.user;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String email;
    private String role;
}
