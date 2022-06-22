package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.*;

import java.util.List;

public interface InstanceManagement {
    Instance createInstance( Instance instance );
    String startInstance(String instanceId);
    String stopInstance(String instanceId);
    String deleteInstance(String instanceId);
    Instance getInstanceDetails(String instanceId);

    List<Instance> getInstancesByUserId( Integer id );
}
