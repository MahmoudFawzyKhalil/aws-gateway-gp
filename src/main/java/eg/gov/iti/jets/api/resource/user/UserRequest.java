package eg.gov.iti.jets.api.resource.user;

import lombok.Data;

@Data
public class UserRequest {

    private Integer id;

    private String username;

    private String email;

    private String password;

 //   private String role ;

    private RoleType role;
//
//    private List<Track> tracks;
//
//    private List<Instance> createdInstances = new ArrayList<>();
//
//    private List<Instance> grantedInstances = new ArrayList<>();

}
