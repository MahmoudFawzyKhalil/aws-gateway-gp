package eg.gov.iti.jets.api.resource.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    @NotBlank(message = "must not be empty or null")
    private String name;
    private List<Integer> privileges;
}
