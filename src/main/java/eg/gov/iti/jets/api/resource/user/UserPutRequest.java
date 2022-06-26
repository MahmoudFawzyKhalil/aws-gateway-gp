package eg.gov.iti.jets.api.resource.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPutRequest {
    String oldPassword;
    String newPassword;
}
