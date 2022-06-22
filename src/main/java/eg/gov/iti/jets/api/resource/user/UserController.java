package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserManagementImpl userManagement;
    private final Mapper mapper;

    public UserController(UserManagementImpl userManagement , Mapper mapper){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@AuthenticationPrincipal UserAdapter userAdapter ,
                                                   @Valid @RequestBody CreateUserRequest userRequest){
        int currentLoggedUserId = userAdapter.getId();
        User user = mapper.createUserRequestToUser(currentLoggedUserId,userRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.mapFromUserToUserResponse(userManagement.createUser(user)));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id){
        User user = userManagement.getUserById(id);
        UserResponse response = mapper.mapFromUserToUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser (@PathVariable int id ,
                                                    @Valid @RequestBody UpdateUserRequest userRequest ,
                                                    @AuthenticationPrincipal UserAdapter userAdapter){
        int currentLoggedUserId= userAdapter.getId();
        User user = mapper.updateUserRequestToUser(currentLoggedUserId,id,userRequest);
        return ResponseEntity.ok(mapper.mapFromUserToUserResponse(userManagement.updateUser(user)));
    }

    @GetMapping("/students")
    public ResponseEntity<UserResponseList> getStudents(){
        List<User> users = userManagement.getAllStudentUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return ResponseEntity.ok(userResponseList);
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponseList> getUsers(){
        List<User> users = userManagement.getAllUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return ResponseEntity.ok(userResponseList);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser( @PathVariable int id){
        return userManagement.deleteUser( id );
    }

    @GetMapping("/tracks/{id}/students")
    public ResponseEntity<UserResponseList> getUserStudents(@PathVariable Integer id) {
        Track track = new Track();
        track.setId(id);
        UserResponseList userResponseList = new UserResponseList();
        userResponseList.setUserResponsesList(
                userManagement.getTrackStudents(track)
                        .stream().map(mapper::mapFromUserToUserResponse)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(userResponseList);
    }

//    @GetMapping("/users/{id}/instructors")
//    public UserResponseList getSupervisorInstructors(@PathVariable Integer id) {
//        User user = new User();
//        user.setId(id);
//        UserResponseList userResponseList = new UserResponseList();
//        userResponseList.setUserResponsesList(        userManagement.getSupervisorInstructors(user)
//                .stream().map(mapper::mapFromUserToUserResponse)
//                .collect(Collectors.toList()));
//        return userResponseList;
//    }

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
