package eg.gov.iti.jets.service.gateway.aws.ec2;

import eg.gov.iti.jets.persistence.entity.aws.*;

import java.util.List;
import java.util.Optional;

public interface AwsGateway {

    /**
     * @return Lists available VPCs for the account
     */

    List<Vpc> describeVpcs();

    /**
     * An EC2 instance must belong to a specific subnet, and a subnet must belong to a specific VPC.
     *
     * @return List of all available subnets in the account.
     */
    List<Subnet> describeAllSubnets();

    /**
     * Creates a key pair with the given name. A key pair is later used to create an EC2 instance. To be able to access a Linux EC2 instance via SSH the key pair's {@link KeyPair#keyMaterial} must be available to the user of our system to be downloaded as a .pem or .ppk file.
     *
     * @return KeyPair must be saved immediately to the database as the {@link KeyPair#keyMaterial} (the secret key) can't be obtained again through any other method.
     */
    KeyPair createKeyPair(String keyName);// must be saved immediately to db upon being obtained

    /**
     * An EC2 instance must belong to a particular subnet in a particular VPC.
     * To see a list of available subnets see {@link AwsGateway#describeAllSubnets}.
     * Then from the subnet grab the vpcId of the subnet and use it in this method to see the available security groups for the subnet's VPC.
     */
    List<SecurityGroup> describeSecurityGroupsForVpc(String vpcId);

    List<SecurityGroup> describeSecurityGroupsForIds(List<String> securityGroupIds);

    List<SecurityGroup> describeSecurityGroupsForNames(List<String> securityGroupNames);

    /**
     * @param instance Specify the instance you want to start (the instanceId and timeToLive must be provided)
     * @return The current state of that instance
     */
    String startInstance(Instance instance);

    /**
     * @param instanceId Specify the id of instance you want to stop
     * @return The current state of that instance
     */
    String stopInstance(String instanceId);

    /**
     * @param instanceId Specify the id of instance you want to terminate
     * @return The current state of that instance
     */
    String terminateInstance(String instanceId);


    /**
     * Creates instance according to a predefined template.
     *
     * @param template            A predefined template that describes the instance that will be created
     * @param instanceName        The name of the instance that will be created
     * @param keyPair             The keyPair attached to the instance
     * @param timeToLiveInMinutes The maximum time the instance is up before shutting down
     * @return the newly created instance
     */
    Instance createInstance(TemplateConfiguration template, String instanceName, KeyPair keyPair, Long timeToLiveInMinutes);

    /**
     * Describes an already created EC2 instance
     *
     * @param instanceId The ID of the instance to be described
     * @return an Optional describes the instance
     */
    Optional<Instance> describeInstance(String instanceId);

    /**
     * Describes a list of the already created EC2 instances
     *
     * @param instanceIds The list of instance IDs to be described
     * @return List of instances
     */
    List<Instance> describeInstances(List<String> instanceIds);

    /**
     * Gets available instance types
     *
     * @return List of instance types as a {@code String}
     */
    List<String> getInstanceTypes();

    /**
     * Describes aws ec2 image
     *
     * @param amiId The id of the image to be described
     * @return an Optional describes the image
     */
    Optional<Ami> describeAmi(String amiId);


    /**
     * Updates instance info from aws, NOTICE: only instance public ip, DNS name and state are updated
     *
     * @param instance The instance to be updated
     */
    void updateInstanceInfoFromAws(Instance instance);

    /**
     * Updates instances info from aws, NOTICE: only instances public ips, DNS names and states are updated
     *
     * @param instances The instances to be updated
     */
    void updateInstancesInfoFromAws(List<Instance> instances);
}
