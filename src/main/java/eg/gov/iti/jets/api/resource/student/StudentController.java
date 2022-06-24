package eg.gov.iti.jets.api.resource.student;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.impl.StudentManagementImpl;
import eg.gov.iti.jets.service.model.UserAdapter;
import eg.gov.iti.jets.persistence.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentManagementImpl studentManagement;
    private final Mapper mapper;

    public StudentController(StudentManagementImpl studentManagement , Mapper mapper){
        this.studentManagement = studentManagement;
        this.mapper = mapper;
    }

    @GetMapping
    //instructor - supervisor
    public ResponseEntity<StudentResponseList> getStudent(){
        List<User> student = studentManagement.getAllStudent();
        List<StudentResponse> studentResponses =  mapper.mapFromListOfStudentToListOfStudentResponses(student);
        StudentResponseList studentResponseList = new StudentResponseList();
        for ( StudentResponse response : studentResponses ) {
            studentResponseList.getStudentResponsesList().add( response );
        }
        return new ResponseEntity<>( studentResponseList , HttpStatus.OK );
    }


    @PostMapping
    //supervisor - instructor
    public ResponseEntity createStudents( @AuthenticationPrincipal UserAdapter userAdapter ,
                                          @Valid @RequestBody StudentListRequest studentListRequest){
        int currentLoggedUserId = userAdapter.getId();
        studentManagement.addStudents(mapper.mapFromStudentListRequestToStudentList(currentLoggedUserId,studentListRequest));
        return new ResponseEntity("Students Inserted Successfully",HttpStatus.CREATED);
    }





}


