package eg.gov.iti.jets.api.resource.student;


import eg.gov.iti.jets.service.management.StudentManagement;
import eg.gov.iti.jets.service.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    final
    StudentManagement studentManagement;

    public StudentController( StudentManagement studentManagement ) {
        this.studentManagement = studentManagement;
    }

    @PostMapping()
    public Boolean createStudent(@RequestBody Student student){
        return studentManagement.createStudent( student );
    }

    @PutMapping()
    public Student editStudent(@RequestBody Student student){
        return studentManagement.editStudent( student );
    }

    @DeleteMapping()
    public Boolean deleteStudent(@RequestBody Student student){
        return studentManagement.deleteStudent( student );
    }

}
