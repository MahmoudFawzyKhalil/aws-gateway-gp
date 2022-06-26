package eg.gov.iti.jets.api.resource.staff;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffUpdateRequest {

    private String roleName;
    private List<Integer> tracksId;
}
