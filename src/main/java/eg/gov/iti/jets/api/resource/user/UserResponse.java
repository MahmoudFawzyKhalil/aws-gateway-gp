package eg.gov.iti.jets.api.resource.user;


import lombok.Data;
import java.util.List;

@Data
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String role;
    private List<UserTrackResponse> tracks;
}
