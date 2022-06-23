package eg.gov.iti.jets.api.resource.student;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.impl.StudentManagementImpl;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentManagementImpl studentManagement;
    private final Mapper mapper;

    public StudentController(StudentManagementImpl studentManagement, Mapper mapper){

        this.studentManagement = studentManagement;
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
        studentManagement.addStudent(mapper.mapFromStudentRequestToStudent(studentRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


