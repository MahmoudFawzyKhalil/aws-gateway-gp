package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserManagementImpl userManagement;
    final Mapper mapper;

    public UserController(UserManagementImpl userManagement , Mapper mapper){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }


    @PostMapping
    public String createUser( @RequestBody UserRequest userRequest){
        return userManagement.createUser(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id){
        return userManagement.getUserById(id);
    }


    @PutMapping
    public String updateUser (@RequestBody UserRequest userRequest){
        return userManagement.updateUser( userRequest );
    }

    @DeleteMapping("/{id}")
    public String deleteUser( @PathVariable int id){
        return userManagement.deleteUser( id );
    }

    //
//    @GetMapping("/{name}")
//    public UserResponse getUserByName(@PathVariable String username){
//        return mapper.mapFromUserToUserResponse( userManagement.getUserByName( username ) );
//    }
//
    @GetMapping
    public List<UserResponse> getUsers(){
        return userManagement.getAllUsers();
    }
}
