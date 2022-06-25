package eg.gov.iti.jets.api.resource.staff;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequestList {
    List<StaffRequest> staffRequests=new ArrayList<>();
}
