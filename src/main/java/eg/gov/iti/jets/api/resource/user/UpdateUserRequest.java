package eg.gov.iti.jets.api.resource.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "Username should not be empty or null")
    @Size(min = 3, max = 25 , message = "User name length should be between 3 and 25 characters")
    private String username;
    @NotBlank(message = "Email should not be empty or null")
    @Email(message = "Email should be in this format example@example.example")
    private String email;
    @NotBlank(message = "Password should not be empty or null")
    private String password;
    private RoleType role;
}
