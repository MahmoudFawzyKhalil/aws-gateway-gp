package eg.gov.iti.jets.api.resource.instanceType;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.InstanceTypeAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instnacetype")
public class InstanceTypeController {
    @Autowired
    InstanceTypeAws instanceTypeAws;
    @Autowired
    Mapper mapper;

    @GetMapping()
    InstanceTypeObjectResponse getInstanceTypes(){
        return  mapper.mapFromInstanceTypeToObjectResponse( instanceTypeAws.getInstanceTypes() );
    }
}
