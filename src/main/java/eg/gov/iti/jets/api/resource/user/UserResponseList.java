package eg.gov.iti.jets.api.resource.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseList {
    private List<UserResponse> userResponsesList = new ArrayList<>();
}
