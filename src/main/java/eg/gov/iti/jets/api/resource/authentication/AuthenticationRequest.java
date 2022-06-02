package eg.gov.iti.jets.api.resource.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}