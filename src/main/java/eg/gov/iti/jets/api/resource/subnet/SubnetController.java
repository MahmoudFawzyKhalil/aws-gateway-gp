package eg.gov.iti.jets.api.resource.subnet;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.SubnetAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subnet")
// TODO: 6/17/2022 ashraf dh el supervisor
public class SubnetController {
    final
    Mapper mapper;
    final
    SubnetAws subnetAws;

    public SubnetController( Mapper mapper, SubnetAws subnetAws ) {
        this.mapper = mapper;
        this.subnetAws = subnetAws;
    }

    @GetMapping()
    ResponseEntity<?> getAllSubnet(){
        // TODO: 6/17/2022 hena mmkn ydrab haga ??
        SubnetObjectResponse subnetObjectResponse = mapper.mapFromSubnetToSubnetResponse( subnetAws.getAllSubnets() );
        return new ResponseEntity<>(subnetObjectResponse , HttpStatus.OK) ;

    }
}
