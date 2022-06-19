package eg.gov.iti.jets.service.gateway.aws.ec2.impl;


import eg.gov.iti.jets.persistence.entity.aws.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AwsGatewayImplTest {
    static AwsGatewayImpl awsGateway;

    @BeforeAll
    public static void test(){
        awsGateway= new AwsGatewayImpl( Ec2Client.builder().region( Region.US_EAST_1 ).credentialsProvider(        ProfileCredentialsProvider.create())
                .build() );
    }
    @Test
    @Disabled
    public void describeVpcs() {
        assertNotEquals(0 , awsGateway.describeVpcs().size()  );
    }

    @Test
    @Disabled
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
    @Disabled
    void describeSecurityGroupsForVpc() {
        String s = "vpc-0568de5e397124e85";
        List<SecurityGroup> securityGroups = awsGateway.describeSecurityGroupsForVpc( s );
        assertNotEquals( 0 , securityGroups.size() );
    }

    @Test
    @Disabled
    void startInstance() {
        Instance instance = new Instance();
        instance.setInstanceId( "i-064bcb93e8b674643" );
        awsGateway.startInstance( instance ) ;
    }

    @Test
    @Disabled
    void stopInstance() {
        awsGateway.stopInstance( "i-064bcb93e8b674643" );
    }

    @Test
    @Disabled
    void terminateInstance() {
        awsGateway.terminateInstance( "i-064bcb93e8b674643" ) ;
    }

    @Test
//    @Disabled
    void createInstance() {
//        List<String> securityGroupNames = new ArrayList<>();
//        securityGroupNames.add( "sg-03dcd9906dcb0a772" );
        SecurityGroup e1 = new SecurityGroup();
//        e1.setName( "launch-wizard-1" );
        e1.setSecurityGroupId( "sg-03dcd9906dcb0a772" );

        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setInstanceType( "t2.micro" );
        templateConfiguration.setAmiId( "ami-0022f774911c1d690" );
        templateConfiguration.setSecurityGroups( List.of( e1 ) );
        templateConfiguration.setSubnetId( "subnet-0ba09c918db78df91" );

        KeyPair keyPair = new KeyPair();
        keyPair.setKeyName( "key1" );
        Instance instance = awsGateway.createInstance( templateConfiguration, "fawzy" , keyPair ,1l);
        assertNotEquals( null , instance );

    }


    @Test
    @Disabled
    void describeInstance() {
        Instance instance = awsGateway.describeInstance( "i-064bcb93e8b674643" ).get();
        assertNotEquals( null , instance );
    }

    @Test
    @Disabled
    void getInstanceTypes() {
        assertNotEquals( 0 , awsGateway.getInstanceTypes().size() );
    }



}
