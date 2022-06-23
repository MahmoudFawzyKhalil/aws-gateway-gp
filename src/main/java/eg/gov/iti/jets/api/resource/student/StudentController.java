package eg.gov.iti.jets.api.resource.student;

import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.track.TrackResponseList;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.resource.user.UserResponseList;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.impl.StudentManagementImpl;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.StudentManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentManagementImpl studentManagement;
    private final Mapper mapper;

    public StudentController(StudentManagement studentManagement , Mapper mapper){
        this.studentManagement = studentManagement;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<StudentResponseList> getStudent(){
        List<User> student = studentManagement.getAllStudent();
        List<StudentResponse> studentResponses =  mapper.mapFromListOfStudentToListOfStudentResponses(student);
        StudentResponseList studentResponseList = new StudentResponseList();
        for ( StudentResponse response : studentResponses ) {
            studentResponseList.getStudentResponsesList().add( response );
        }
        return new ResponseEntity<>( studentResponseList , HttpStatus.OK );
    }


}


