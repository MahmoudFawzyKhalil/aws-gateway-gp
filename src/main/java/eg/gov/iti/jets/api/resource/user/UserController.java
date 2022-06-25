package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import eg.gov.iti.jets.service.model.UserAdapter;

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
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_USERS.name())")
    public ResponseEntity<?> getUsers(){
        System.out.println("hey");
        List<User> users = userManagement.getAllUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return ResponseEntity.ok(userResponseList);
    }

    @GetMapping("/profile")
    public  ResponseEntity<?> getUserInfo( @AuthenticationPrincipal UserAdapter userDetails ){
        Integer userId = userDetails.getId();
        User user = userManagement.getUserInfo(userId);
        UserResponse userResponse = mapper.mapFromUserToUserResponse( user );
        return new ResponseEntity<>(userResponse , HttpStatus.OK);
    }
}
