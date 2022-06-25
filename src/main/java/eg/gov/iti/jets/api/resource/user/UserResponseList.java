package eg.gov.iti.jets.api.resource.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseList {
    private List<UserResponse> userResponsesList = new ArrayList<>();
}
