package eg.gov.iti.jets.api.resource.instanceType;

import eg.gov.iti.jets.service.management.InstanceTypeAws;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: 6/17/2022 Ashraf el supervisor bs
@RestController
@RequestMapping("/api/instanceType")
public class InstanceTypeController {
    private final
    InstanceTypeAws instanceTypeAws;
    private final InstanceTypeMapper instanceTypeMapper;

    public InstanceTypeController( InstanceTypeAws instanceTypeAws, InstanceTypeMapper instanceTypeMapper ) {
        this.instanceTypeAws = instanceTypeAws;
        this.instanceTypeMapper = instanceTypeMapper;
    }


    @GetMapping()
    ResponseEntity<?> getInstanceTypes(){
        // TODO: 6/17/2022 hal hena mmkn myrga3sh nhandle haga ??
        InstanceTypeObjectResponse instanceTypeObjectResponse = instanceTypeMapper.mapFromInstanceTypeToObjectResponse( instanceTypeAws.getInstanceTypes() );
        return  new ResponseEntity<>( instanceTypeObjectResponse , HttpStatus.OK );
    }
}
