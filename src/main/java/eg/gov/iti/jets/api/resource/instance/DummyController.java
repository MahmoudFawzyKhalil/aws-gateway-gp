package eg.gov.iti.jets.api.resource.instance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dummystudent")
public class DummyController {

    @GetMapping
    public DummyResponse listOfStudent(){
        DummyObjectResponse dummyObjectResponse = new DummyObjectResponse(1,"Mariam");
        DummyObjectResponse dummyObjectResponse1 = new DummyObjectResponse(2,"Marwa");
        DummyObjectResponse dummyObjectResponse2 = new DummyObjectResponse(3,"Halla");
        DummyObjectResponse dummyObjectResponse3 = new DummyObjectResponse(4,"Hend");
        return new DummyResponse(new ArrayList<>(List.of( dummyObjectResponse, dummyObjectResponse1, dummyObjectResponse2, dummyObjectResponse3 )));
    }

}
