package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserManagementImpl userManagement;
    private final Mapper mapper;

    public UserController(UserManagementImpl userManagement , Mapper mapper ){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }

    @PostMapping("/users")
    public UserResponse createUser( @RequestBody CreateUserRequest userRequest){
        User user = mapper.createUserRequestToUser(userRequest);
        return mapper.mapFromUserToUserResponse(userManagement.createUser(user));
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable int id){
        User user = userManagement.getUserById(id);
        UserResponse response = mapper.mapFromUserToUserResponse(user);
        return response;
    }

    @PutMapping("/users")
    public UserResponse updateUser (@RequestBody UpdateUserRequest userRequest){
        User user = mapper.updateUserRequestToUser(userRequest);
        return mapper.mapFromUserToUserResponse(userManagement.updateUser(user));
    }

    @GetMapping("/students")
    public UserResponseList getStudents(){
        List<User> users = userManagement.getAllStudentUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return userResponseList;
    }

    @GetMapping("/users")
    public UserResponseList getUsers(){
        List<User> users = userManagement.getAllUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return userResponseList;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser( @PathVariable int id){
        return userManagement.deleteUser( id );
    }

    @GetMapping("/users/{id}/students")
    public UserResponseList getUserStudents(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        UserResponseList userResponseList = new UserResponseList();
        userResponseList.setUserResponsesList(
                userManagement.getUserStudents(user)
                        .stream().map(mapper::mapFromUserToUserResponse)
                        .collect(Collectors.toList())
        );
        return userResponseList;
    }

    @GetMapping("/users/{id}/instructors")
    public UserResponseList getSupervisorInstructors(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        UserResponseList userResponseList = new UserResponseList();
        userResponseList.setUserResponsesList(        userManagement.getSupervisorInstructors(user)
                .stream().map(mapper::mapFromUserToUserResponse)
                .collect(Collectors.toList()));
        return userResponseList;
    }

    /**
     * todo
     * assign privilege to user
     */

//    @GetMapping("/users/{id}/followers")
//    public UserResponseList getAllUserFollowers(@PathVariable Integer id) {
//        User user = new User();
//        user.setId(id);
//        UserResponseList userResponseList = new UserResponseList();
//        userResponseList.setUserResponsesList(
//                userManagement.getAllUserFollowers(user)
//                .stream().map(mapper::mapFromUserToUserResponse)
//                        .collect(Collectors.toList())
//        );
//        return userResponseList;
//    }

//    @GetMapping("/{name}")
//    public UserResponse getUserByName(@PathVariable String username){
//        return mapper.mapFromUserToUserResponse( userManagement.getUserByName( username ) );
//    }
//
}
