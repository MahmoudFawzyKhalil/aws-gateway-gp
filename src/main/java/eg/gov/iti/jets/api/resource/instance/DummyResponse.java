package eg.gov.iti.jets.api.resource.instance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DummyResponse {

    private List<DummyObjectResponse> dummyObjectResponses = new ArrayList<>();
}
