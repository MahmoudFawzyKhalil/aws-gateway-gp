package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.Subnet;

import java.util.List;

public interface SubnetAws {
    List<Subnet> getAllSubnets();

}
