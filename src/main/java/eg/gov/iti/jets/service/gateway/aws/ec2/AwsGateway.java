package eg.gov.iti.jets.service.gateway.aws.ec2;

import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.model.CreateInstanceCommand;
import eg.gov.iti.jets.service.model.DescribeSecurityGroupsCommand;
import eg.gov.iti.jets.service.model.DescribeSubnetsCommand;
import software.amazon.awssdk.services.ec2.Ec2Client;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

public interface AwsGateway {
    /**
     * @return Lists available VPCs in Ec2 service
     */
    List<Vpc> describeVpcs();

    /**
     * @param subnetIds subnetIds to retrieve details for
     * @return List of available Subnets in Ec2 service
     */
    List<Subnet> describeSubnets(List<String> subnetIds);
    /**
     * @return List of available Subnets in Ec2 service
     */
    List<Subnet> describeAllSubnets();

    /**
     * Creates a key pair with the given name. A key pair is later used to create an EC2 instance. To be able to access a Linux EC2 instance via SSH the key pair's {@link KeyPair#keyMaterial} must be available to the user of our system to be downloaded as a .pem or .ppk file.
     *
     * @return KeyPair must be saved immediately to the database as the {@link KeyPair#keyMaterial} (the secret key) can't be obtained again through any other method.
     */
    KeyPair createKeyPair(String keyName);// must be saved immediately to db upon being obtained

    /**
     * @param securityGroupIds Security group ids for which to return details
     * @return List of available SecurityGroups in Ec2 service
     */
    List<SecurityGroup> describeSecurityGroups(List<String> securityGroupIds);
    /**
     * @return List of available security groups in Ec2 service
     */
    List<SecurityGroup> describeAllSecurityGroups();

    /**
     * @param instanceId Specify the id of instance you want to start
     * @return The current state of that instance
     */
    String startInstance(String instanceId);

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
     * Creates a custom EC2 instance.
     * @param command Describes the instance to be created
     * @return The newly created instance
     */
    Instance createInstance(CreateInstanceCommand command);

    /**
     * Creates instance according to a predefined template.
     * @param template A predefined template that describes the instance that will be created
     * @param instanceName The name of the instance that will be created
     * @return the newly created instance
     */
    Instance createInstance(TemplateConfiguration template, String instanceName);

    /**
     * Describes an already created EC2 instance
     * @param instanceId The ID of the instance to be described
     * @return an Optional describes the instance
     */
    Optional<Instance> describeInstance(String instanceId);

    /**
     * Describes a list of the already created EC2 instances
     * @param instanceIds The list of instance IDs to be described
     * @return List of instances
     */
    List<Instance> describeInstances(List<String> instanceIds);


    String createWindowsEc2Instance(Ec2Client ec2Client,String keyName,String ami);

    void saveInstancePasswordIntoFile(Ec2Client ec2Client,String instanceId);

    String decryptPassword(String encryptedPassword) throws Exception;

    String getKeyMaterialFromKeyPair(Ec2Client ec2Client,String keyName);
}
