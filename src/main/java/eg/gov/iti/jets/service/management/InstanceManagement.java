package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.*;

import java.util.List;
import java.util.Optional;

public interface InstanceManagement {
    Instance createInstance( Instance instance );
    String startInstance(String instanceId);
    String stopInstance(String instanceId);
    String deleteInstance(String instanceId);
    Instance instanceDetails (String instanceId);
}
