package eg.gov.iti.jets.service.management;

import com.sun.xml.bind.v2.TODO;
import eg.gov.iti.jets.service.model.Branch;
import eg.gov.iti.jets.service.model.Student;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class StudentManagement {

    public Boolean createStudent( Student student){
        return null;
    }

    public Student updateStudent( Student student ){
        return null;
    }

    public Boolean deleteStudent ( int id){
        return null;
    }

    public List<Student> getAllStudents(){
        return null;
    }

    public Student getStudentById( int id ){
        return null;
    }

    public Boolean readFromCSV ( InputStream inputStream ){

        return null;
    }

    //TODO checked if in track before
    //TODO csv file processing
}
