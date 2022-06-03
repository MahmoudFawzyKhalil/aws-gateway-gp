package eg.gov.iti.jets.service.gateway.aws.ec2;

import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.model.*;

import java.util.List;
import java.util.Optional;

public interface AwsGateway {


    List<Vpc> describeVpcs(DescribeVpcsCommand command);

    List<Subnet> describeSubnets(DescribeSubnetsCommand command);

    KeyPair createKeyPair(CreateKeyPairCommand command) ;// must be saved immediately to db upon being obtained

    List<SecurityGroup> describeSecurityGroups(DescribeSecurityGroupsCommand command);

    String startInstance(String instanceId);
    String stopInstance(String instanceId);
    String terminateInstance(String instanceId);



    Instance createInstance(CreateInstanceCommand command);
    Instance createInstance(TemplateConfiguration template);

    Optional<Instance> describeInstance( String instanceId);
    List<Instance> describeInstances(DescribeInstancesCommand command);
}
