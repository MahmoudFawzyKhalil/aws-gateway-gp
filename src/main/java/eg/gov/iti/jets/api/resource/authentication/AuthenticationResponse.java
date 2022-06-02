package eg.gov.iti.jets.api.resource.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private final String jwt;
}