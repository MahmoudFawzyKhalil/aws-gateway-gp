package eg.gov.iti.jets.service.management;

import com.sun.xml.bind.v2.TODO;
import eg.gov.iti.jets.service.model.Branch;
import eg.gov.iti.jets.service.model.Student;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentManagement {

    public Boolean createStudent( Student student ) {
        return null;
    }

    public Student updateStudent( Student student ) {
        return null;
    }

    public Boolean deleteStudent( int id ) {
        return null;
    }

    public List<Student> getAllStudents() {
        return null;
    }

    public Student getStudentById( int id ) {
        return null;
    }

    public Boolean createStudentFromCSV( String csvFile ) {
        List<String> strings = readCsvFile( csvFile );

        return null;
    }

    private List<String> readCsvFile( String csvFile ) {
        final String delimiter = ",";
        List<String> tempArr = new ArrayList<>();
        try ( var br = new BufferedReader( new FileReader( csvFile ) ) ) {
            String line;

            while ( ( line = br.readLine() ) != null ) {
                tempArr.add( line ) ;
            }
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        return tempArr;
    }

    //TODO checked if in track before
    //TODO csv file processing
}
