package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.*;

import java.util.List;
import java.util.Optional;

public interface InstanceManagement {
    Optional<Instance> createInstance( int templateConfigurationId, String instanceName , String keyPair);
}
