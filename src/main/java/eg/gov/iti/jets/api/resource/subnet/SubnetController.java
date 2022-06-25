package eg.gov.iti.jets.api.resource.subnet;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.SubnetAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subnet")
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
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TEMPLATES.name())")
    ResponseEntity<?> getAllSubnet(){
        SubnetObjectResponse subnetObjectResponse = mapper.mapFromSubnetToSubnetResponse( subnetAws.getAllSubnets() );
        return new ResponseEntity<>(subnetObjectResponse , HttpStatus.OK) ;
    }
}
