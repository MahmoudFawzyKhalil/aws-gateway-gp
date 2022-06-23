package eg.gov.iti.jets.api.resource.student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentResponse {
    private String track;
    private String userName;
    private String email;
    private String password;
    private String role;
    private int id;

}
