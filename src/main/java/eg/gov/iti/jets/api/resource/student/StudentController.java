package eg.gov.iti.jets.api.resource.student;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.StudentManagement;
import eg.gov.iti.jets.service.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    final
    StudentManagement studentManagement;
    final Mapper mapper;

    public StudentController( StudentManagement studentManagement , Mapper mapper) {
        this.studentManagement = studentManagement;
        this.mapper = mapper;
    }

    @PostMapping()
    public Boolean createStudent(@RequestBody StudentRequest student){
        return studentManagement.createStudent( mapper.mapFromStudentRequestToStudent(student) );
    }

    @PutMapping()
    public Student editStudent(@RequestBody StudentRequest student){
        return studentManagement.updateStudent( mapper.mapFromStudentRequestToStudent(student) );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteStudent(@PathVariable int id){
        return studentManagement.deleteStudent( id );
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentManagement.getAllStudents().stream()
                .map( mapper::mapFromStudentToStudentResponse )
                .collect( Collectors.toList() );
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable int id){
        Student student = studentManagement.getStudentById( id );
        return mapper.mapFromStudentToStudentResponse( student );

    }

}
