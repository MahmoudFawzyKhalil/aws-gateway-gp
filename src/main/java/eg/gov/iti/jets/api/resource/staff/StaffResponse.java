package eg.gov.iti.jets.api.resource.staff;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {
    private Integer id;
    private String username;
    private String email;
    private List<String> tracks;

}
