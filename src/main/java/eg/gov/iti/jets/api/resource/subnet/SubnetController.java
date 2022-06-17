package eg.gov.iti.jets.api.resource.subnet;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.SubnetAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subnet")
public class SubnetController {
    @Autowired
    Mapper mapper;
    @Autowired
    SubnetAws subnetAws;

    @GetMapping()
    SubnetObjectResponse getAllSubnet(){
        return  mapper.mapFromSubnetToSubnetResponse(subnetAws.getAllSubnets());

    }
}
