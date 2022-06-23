package eg.gov.iti.jets.api.resource.student;

import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.track.TrackResponseList;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.resource.user.UserResponseList;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final UserManagementImpl userManagement;
    private final Mapper mapper;

    public StudentController(UserManagementImpl userManagement , Mapper mapper){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }

//    @GetMapping
//    public ResponseEntity<StudentResponseList> getUsers(){
//        List<User> users = userManagement.getAllUsers();
//        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
//        UserResponseList studentResponseList = new UserResponseList();
//        for(UserResponse response : userResponses){
//            studentResponseList.getUserResponsesList().add(response);
//        }
//        return new ResponseEntity<>( studentResponseList , HttpStatus.OK );
//    }



























































    @PostMapping
    public ResponseEntity createStudent(@RequestBody StudentRequest studentRequest){



        return new ResponseEntity<>(HttpStatus.OK);
    }
}


