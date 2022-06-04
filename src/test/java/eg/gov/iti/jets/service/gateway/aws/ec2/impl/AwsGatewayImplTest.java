package eg.gov.iti.jets.service.gateway.aws.ec2.impl;


import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Vpc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsResponse;


import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class AwsGatewayImplTest {
    static AwsGatewayImpl awsGateway;

    @BeforeAll
    public static void test(){
        awsGateway= new AwsGatewayImpl( Ec2Client.builder().region( Region.US_EAST_1 ).credentialsProvider(        ProfileCredentialsProvider.create())
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
    void createKeyPair() {
        String name = "3am-Mina";
        KeyPair keyPair = awsGateway.createKeyPair( name );
        assertEquals( name , keyPair.getKeyName() );
    }

    @Test
    void describeSecurityGroupsForVpc() {
        String s = "vpc-0aa80433afd8b312c";
        List<SecurityGroup> securityGroups = awsGateway.describeSecurityGroupsForVpc( s );
        assertNotEquals( 0 , securityGroups.size() );
    }

    @Test
    void startInstance() {
    }

    @Test
    void stopInstance() {
    }

    @Test
    void terminateInstance() {
    }

    @Test
    void createInstance() {
    }

    @Test
    void testCreateInstance() {
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
