package eg.gov.iti.jets.service.gateway.aws.ec2;

import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.model.*;

import java.util.List;
import java.util.Optional;

public interface AwsGateway {

    List<Vpc> describeVpcs(DescribeVpcsCommand command);

    List<Subnet> describeSubnets(DescribeSubnetsCommand command);

    /**
     * Creates a key pair with the given name. A key pair is later used to create an EC2 instance. To be able to access a Linux EC2 instance via SSH the key pair's {@link KeyPair#keyMaterial} must be available to the user of our system to be downloaded as a .pem or .ppk file.
     *
     * @return KeyPair must be saved immediately to the database as the {@link KeyPair#keyMaterial} (the secret key) can't be obtained again through any other method.
     */
    KeyPair createKeyPair(String keyName );// must be saved immediately to db upon being obtained

    List<SecurityGroup> describeSecurityGroups(DescribeSecurityGroupsCommand command);

    String startInstance(String instanceId);

    String stopInstance(String instanceId);

    String terminateInstance(String instanceId);

    Instance createInstance( CreateInstanceCommand command);

    Instance createInstance( TemplateConfiguration template, String instanceName);

    Optional<Instance> describeInstance(String instanceId);

    List<Instance> describeInstances(List<String> instanceIds);
}
