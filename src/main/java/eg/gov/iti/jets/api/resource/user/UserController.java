package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.web.bind.annotation.*;


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
    public Boolean createUser( @RequestBody UserRequest userRequest){
        return userManagement.createUser( mapper.mapFromUserRequestToUser( userRequest )  );
    }

    @PutMapping
    public UserResponse updateUser (@RequestBody UserRequest userRequest){
        User user = userManagement.updateUser( mapper.mapFromUserRequestToUser( userRequest ) );
        return mapper.mapFromUserToUserResponse( user );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser( @PathVariable int id){
        return userManagement.deleteUser( id );
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id){
        return userManagement.getUserById(id);
    }

//
//    @GetMapping("/{name}")
//    public UserResponse getUserByName(@PathVariable String username){
//        return mapper.mapFromUserToUserResponse( userManagement.getUserByName( username ) );
//    }
//
//    @GetMapping
//    public List<UserResponse> getUsers(){
//        return userManagement.getAllUser()
//                .stream().map( mapper::mapFromUserToUserResponse )
//                .collect( Collectors.toList() );
//    }
//
//
//    @GetMapping
//    public String getUsers(){
//        return "users";
//    }
}
