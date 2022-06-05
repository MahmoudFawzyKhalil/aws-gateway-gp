package eg.gov.iti.jets.api.resource.instance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/dummystudent")
public class DummyController {

    @GetMapping
    public List<DummyResponse> listOfStudent(){
        DummyResponse dummyResponse = new DummyResponse(1,"Mariam");
        DummyResponse dummyResponse1 = new DummyResponse(2,"Marwa");
        DummyResponse dummyResponse2 = new DummyResponse(3,"Halla");
        DummyResponse dummyResponse3 = new DummyResponse(4,"Hend");
        return new ArrayList<>(List.of(dummyResponse,dummyResponse1, dummyResponse2 , dummyResponse3));
    }

}
