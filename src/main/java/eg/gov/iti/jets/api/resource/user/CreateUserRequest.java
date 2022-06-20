package eg.gov.iti.jets.api.resource.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateUserRequest {
//    private Integer id;

    @NotBlank(message = "Username should not be empty or null")
    @Size(min = 3, max = 14 , message = "User name length should be between 3 and 14 characters")
    private String username;
    @NotBlank(message = "Email should not be empty or null")
    @Email(message = "Email should be in this format example@example.example")
    private String email;
    @NotBlank(message = "Password should not be empty or null")
    private String password;
    private RoleType role;
    @NotNull(message = "Manager id should not be null")
    private Integer managerId;
}
