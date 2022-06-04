package eg.gov.iti.jets.service.gateway.aws.ec2.impl;


import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.model.CreateInstanceCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsResponse;
import software.amazon.awssdk.services.ec2.model.InstanceType;


import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class AwsGatewayImplTest {
    static AwsGatewayImpl awsGateway;

    @BeforeAll
    public static void test(){
        awsGateway= new AwsGatewayImpl( Ec2Client.builder().region( Region.EU_WEST_1 ).credentialsProvider(        ProfileCredentialsProvider.create())
                .build() );
    }

    @Test
    public void describeVpcs() {
      assertNotEquals(0 , awsGateway.describeVpcs().size()  );
    }

    @Test
    void describeAllSubnets() {
        assertNotEquals( 0 , awsGateway.describeAllSubnets().size() );
    }

    @Test
    @Disabled
    void createKeyPair() {
        String name = "keyPairTest";
        KeyPair keyPair = awsGateway.createKeyPair( name );
        assertEquals( name , keyPair.getKeyName() );
    }

     KeyPair createKeyPairforAnotherTest() {
        String name = "keyPairTest";
        return awsGateway.createKeyPair( name );
    }

    @Test
    void describeSecurityGroupsForVpc() {
        String s = "vpc-0568de5e397124e85";
        List<SecurityGroup> securityGroups = awsGateway.describeSecurityGroupsForVpc( s );
        assertNotEquals( 0 , securityGroups.size() );
    }

    @Test
    void startInstance() {
      //  awsGateway.startInstance(  )

    }

    @Test
    void stopInstance() {
    }

    @Test
    void terminateInstance() {
    }

    @Test
    void createInstance() {
        List<String> securityGroupNames = new ArrayList<>();
        securityGroupNames.add( "sg-0a0179c23e4cddfb8" );
        SecurityGroup e1 = new SecurityGroup();
//        e1.setName( "launch-wizard-1" );
        e1.setName( "sg-0a0179c23e4cddfb8" );

        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setInstanceType( "t2.micro" );
        templateConfiguration.setAmiId( "ami-0c1bc246476a5572b" );
        templateConfiguration.setSecurityGroups( List.of( e1 ) );
        templateConfiguration.setSubnetId( "subnet-0bbd770f75717747c" );

        KeyPair keyPair = new KeyPair();
        keyPair.setKeyName( "KEY_2" );
        Instance instance = awsGateway.createInstance( templateConfiguration, "mina2" , keyPair );

        assertNotEquals( null , instance );

    }

    @Test
    void describeInstance() {
    }

    @Test
    void describeInstances() {
    }

    @Test
    void getInstanceTypes() {
    }

//    @Test
//    public void describeVpcsFail() {
//      Assertions.assertEquals(awsGateway.describeVpcs().size() , 2 );
//    }



}
