package eg.gov.iti.jets.api.resource.instance;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DummyResponse {
    private List<String> student = new ArrayList<>();
}
