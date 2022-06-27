package eg.gov.iti.jets.api.resource.instance;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInstanceResponse {
    private Integer id;
    private String username;
    private String email;
    private String password;
}
