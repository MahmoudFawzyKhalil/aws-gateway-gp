package eg.gov.iti.jets.api.resource.user;


import eg.gov.iti.jets.persistence.entity.aws.Instance;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {

    private Integer id;

    private String username;

    private String email;

    private String password;

//    private Role role;

    private String role;

    private List<String> privileges;


    private List<Instance> createdInstances = new ArrayList<>();

    private List<Instance> grantedInstances = new ArrayList<>();

}
