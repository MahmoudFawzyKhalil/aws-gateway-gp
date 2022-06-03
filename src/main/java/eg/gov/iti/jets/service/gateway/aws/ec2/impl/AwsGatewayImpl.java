package eg.gov.iti.jets.service.gateway.aws.ec2.impl;

import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.Vpc;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;
import java.util.Optional;

public class AwsGatewayImpl implements AwsGateway {
    @Autowired
    private Ec2Client ec2Client;

    @Override
    public List<Vpc> describeVpcs( DescribeVpcsCommand command ) {
        return null;
    }

    @Override
    public List<Subnet> describeSubnets( DescribeSubnetsCommand command ) {
        return null;
    }

    @Override
    public KeyPair createKeyPair( CreateKeyPairCommand command ) {
        return null;
    }

    @Override
    public List<SecurityGroup> describeSecurityGroups( DescribeSecurityGroupsCommand command ) {
        return null;
    }

    @Override
    public String startInstance( String instanceId ) {
        return null;
    }

    @Override
    public String stopInstance( String instanceId ) {
        return null;
    }

    @Override
    public String terminateInstance( String instanceId ) {
        return null;
    }

    @Override
    public Instance createInstance( CreateInstanceCommand command ) {
        CreateKeyPairResponse keyPairResponse = getCreateKeyPairResponse( command.getKeyPair().getKeyName() );
        KeyPair keyPair = createKeyPair( keyPairResponse );

        RunInstancesRequest runInstancesRequest = RunInstancesRequest.builder()
                .keyName( keyPairResponse.keyName() )
                .imageId( command.getAmiId() )
                .instanceType( command.getInstanceType() )
                .subnetId( command.getVpcId() )
                .maxCount( 1 )
                .minCount( 1 )
                .build();

        RunInstancesResponse runInstancesResponse = ec2Client.runInstances( runInstancesRequest );
        Tag tag = createTag( command, ec2Client, runInstancesResponse );
        return mapCreateInstanceProperties( runInstancesResponse, keyPair, tag );
    }

    private CreateKeyPairResponse getCreateKeyPairResponse( String name ) {
        CreateKeyPairRequest createKeyPairRequest = CreateKeyPairRequest.builder()
                .keyName( name )
                .build();
        CreateKeyPairResponse keyPairResponse = ec2Client.createKeyPair( createKeyPairRequest );
        return keyPairResponse;
    }

    private Instance mapCreateInstanceProperties( RunInstancesResponse runInstancesResponse, KeyPair keyPair, Tag tag ) {
        Instance instance = new Instance();
        instance.setInstanceId( runInstancesResponse.instances().get( 0 ).instanceId() );
        instance.setKeyPair( keyPair );
        instance.setInstanceType( runInstancesResponse.instances().get( 0 ).instanceTypeAsString() );
        instance.setVpcId( runInstancesResponse.instances().get( 0 ).vpcId() );
        instance.setAmiId( runInstancesResponse.instances().get( 0 ).imageId() );
        instance.setName( tag.value() );
        instance.setSubnetId( runInstancesResponse.instances().get( 0 ).subnetId() );
        return instance;
    }

    private KeyPair createKeyPair( CreateKeyPairResponse keyPairResponse ) {
        KeyPair keyPair = new KeyPair();
        keyPair.setKeyPairId( keyPairResponse.keyPairId() );
        keyPair.setKeyName( keyPairResponse.keyName() );
        keyPair.setKeyMaterial( keyPairResponse.keyMaterial() );
        return keyPair;
    }

    private Tag createTag( CreateInstanceCommand command, Ec2Client ec2Client, RunInstancesResponse runInstancesResponse ) {
        Tag tag = Tag.builder()
                .key( "Name" )
                .value( command.getTagName() )
                .build();

        CreateTagsRequest tagRequest = CreateTagsRequest.builder()
                .resources( runInstancesResponse.instances().get( 0 ).instanceId() )
                .tags( tag )
                .build();

        ec2Client.createTags( tagRequest );
        return tag;
    }

    @Override
    public Instance createInstance( TemplateConfiguration template ) {
        return null;
    }

    @Override
    public Optional<Instance> describeInstance( String instanceId ) {
        DescribeInstancesRequest request = DescribeInstancesRequest.builder()
                .instanceIds( instanceId )
                .build();

        DescribeInstancesResponse response = ec2Client.describeInstances( request );
        if ( response.hasReservations() && response.reservations().get( 0 ).hasInstances() ) {

            software.amazon.awssdk.services.ec2.model.Instance awsInstance = response.reservations().get( 0 ).instances().get( 0 );
            return Optional.of( mapDescribeInstanceProperties( awsInstance ) );
        }
        return Optional.empty();
    }

    private Instance mapDescribeInstanceProperties( software.amazon.awssdk.services.ec2.model.Instance awsInstance ) {
        Instance instance = new Instance();
        instance.setInstanceId( awsInstance.instanceId() );
        instance.setAmiId( awsInstance.imageId() );
        instance.setInstanceType( awsInstance.instanceTypeAsString() );
        instance.setState( awsInstance.state().nameAsString() );
        instance.setPublicIp( awsInstance.publicIpAddress() );
        return instance;
    }

    @Override
    public List<Instance> describeInstances( DescribeInstancesCommand command ) {
        return null;
    }
}
