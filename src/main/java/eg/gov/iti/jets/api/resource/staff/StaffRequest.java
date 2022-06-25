package eg.gov.iti.jets.api.resource.staff;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequest {
    private String username;
    private String email;

}
