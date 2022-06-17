package eg.gov.iti.jets.api.resource.subnet;

import eg.gov.iti.jets.api.resource.subnet.SubnetResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubnetObjectResponse {
    private List<SubnetResponse> subnetList;
}
