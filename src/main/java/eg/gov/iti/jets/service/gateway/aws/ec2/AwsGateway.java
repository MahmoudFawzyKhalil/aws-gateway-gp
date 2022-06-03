package eg.gov.iti.jets.service.gateway.aws.ec2;

import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.model.*;

import java.util.List;
import java.util.Optional;

public interface AwsGateway {
    /**
     * Returns list of available VPCs in Ec2 service
     */
    List<Vpc> describeVpcs();
    /**
     * @param command describe how to retrieve available subnets
     * @return list of available Subnets in Ec2 service
     */
    List<Subnet> describeSubnets(DescribeSubnetsCommand command);

    /**
     * Creates a key pair with the given name. A key pair is later used to create an EC2 instance. To be able to access a Linux EC2 instance via SSH the key pair's {@link KeyPair#keyMaterial} must be available to the user of our system to be downloaded as a .pem or .ppk file.
     *
     * @return KeyPair must be saved immediately to the database as the {@link KeyPair#keyMaterial} (the secret key) can't be obtained again through any other method.
     */
    KeyPair createKeyPair(String keyName );// must be saved immediately to db upon being obtained
    /**
     * @param command describe how to retrieve available  SecurityGroups
     * @return list of available SecurityGroups in Ec2 service
     */
    List<SecurityGroup> describeSecurityGroups(DescribeSecurityGroupsCommand command);
    /**
     * @param instanceId specify the id of instance you want to start
     * @return the current state of that instance
     */
    String startInstance(String instanceId);
    /**
     * @param instanceId specify the id of instance you want to stop
     * @return the current state of that instance
     */
    String stopInstance(String instanceId);
    /**
     * @param instanceId specify the id of instance you want to terminate
     * @return the current state of that instance
     */
    String terminateInstance(String instanceId);

    Instance createInstance( CreateInstanceCommand command);

    Instance createInstance( TemplateConfiguration template, String instanceName);

    Optional<Instance> describeInstance(String instanceId);

    List<Instance> describeInstances(List<String> instanceIds);
}
