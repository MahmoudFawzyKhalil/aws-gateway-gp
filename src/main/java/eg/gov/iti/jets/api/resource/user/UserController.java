package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserManagementImpl userManagement;
    final Mapper mapper;

    public UserController(UserManagementImpl userManagement , Mapper mapper ){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public UserResponse createUser( @RequestBody UserRequest userRequest){
        User user = mapper.mapFromUserRequestToUser(userRequest);
        return mapper.mapFromUserToUserResponse(userManagement.createUser(user));
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id){
        User user = userManagement.getUserById(id);
        UserResponse response = mapper.mapFromUserToUserResponse(user);
        return response;
    }

    @PutMapping
    public UserResponse updateUser (@RequestBody UserRequest userRequest){
        User user = mapper.mapFromUserRequestToUser(userRequest);
        return mapper.mapFromUserToUserResponse(userManagement.updateUser(user));
    }

    @GetMapping
    public UserResponseList getUsers(){
        List<User> users = userManagement.getAllUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return userResponseList;
    }

    @DeleteMapping("/{id}")
    public String deleteUser( @PathVariable int id){
        return userManagement.deleteUser( id );
    }

//    @GetMapping("/{name}")
//    public UserResponse getUserByName(@PathVariable String username){
//        return mapper.mapFromUserToUserResponse( userManagement.getUserByName( username ) );
//    }
//
}
