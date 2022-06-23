package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserManagementImpl userManagement;
    private final Mapper mapper;

    public UserController(UserManagementImpl userManagement , Mapper mapper){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<UserResponseList> getUsers(){
        List<User> users = userManagement.getAllUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return ResponseEntity.ok(userResponseList);
    }
}
