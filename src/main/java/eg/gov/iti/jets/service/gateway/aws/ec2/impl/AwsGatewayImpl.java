package eg.gov.iti.jets.service.gateway.aws.ec2.impl;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.Vpc;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.exception.AwsGatewayException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.model.*;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class AwsGatewayImpl implements AwsGateway {
    private final Ec2Client ec2Client;

    public AwsGatewayImpl( Ec2Client ec2Client ) {
        this.ec2Client = ec2Client;
    }

    @Override
    public List<Vpc> describeVpcs( ) {

        List<Vpc> vpcs=new ArrayList<>();
        DescribeVpcsResponse describeVpcsResponse = ec2Client.describeVpcs();
        List<software.amazon.awssdk.services.ec2.model.Vpc> awsVpcs = describeVpcsResponse.vpcs();
        for(software.amazon.awssdk.services.ec2.model.Vpc vpc:awsVpcs){
            var vpcmodel=new Vpc();
            vpcmodel.setState(vpc.state().toString());
            vpcmodel.setVpcId(vpc.vpcId());
            vpcmodel.setCidrBlock(vpc.cidrBlock());
            vpcs.add(vpcmodel);
        }

        return vpcs;
    }

    @Override
    public List<Subnet> describeSubnets( DescribeSubnetsCommand command ) {

        List<Subnet> subnets=new ArrayList<>();
        DescribeSubnetsRequest describeSubnetsRequest = DescribeSubnetsRequest.builder()
                .subnetIds(command.getSubnetIds())
                .build();
        DescribeSubnetsResponse describeSubnetsResponse = ec2Client.describeSubnets(describeSubnetsRequest);
        List<software.amazon.awssdk.services.ec2.model.Subnet> subnetList = describeSubnetsResponse.subnets();
        for(software.amazon.awssdk.services.ec2.model.Subnet subnet:subnetList){
            Subnet subnetModel=new Subnet();
            subnetModel.setSubnetId(subnet.subnetId());
            subnetModel.setCidrBlock(subnet.cidrBlock());
            subnetModel.setVpcId(subnet.vpcId());
            subnetModel.setAvailabilityZone(subnet.availabilityZone());
            subnetModel.setAvailabilityZoneId(subnet.availabilityZoneId());
            subnetModel.setMapPublicIpOnLaunch(subnet.mapPublicIpOnLaunch());
            subnets.add(subnetModel);
        }
        return subnets;
    }


    @Override
    public KeyPair createKeyPair( String keyName ) {
        var createKeyPairRequest = CreateKeyPairRequest.builder().keyName( keyName ).build();
        var keyPairResponse = ec2Client.createKeyPair( createKeyPairRequest );
        KeyPair keyPair = new KeyPair();
        keyPair.setKeyPairId( keyPairResponse.keyPairId() );
        keyPair.setKeyName( keyPairResponse.keyName() );
        keyPair.setKeyMaterial( keyPairResponse.keyMaterial() );
        return keyPair;
    }

    @Override
    public List<SecurityGroup> describeSecurityGroups( DescribeSecurityGroupsCommand command ) {

        List<SecurityGroup> securityGroups=new ArrayList<>();
        List<InboundRule> inboundRules=new ArrayList<>();
        DescribeSecurityGroupsRequest describeSecurityGroupsRequest = DescribeSecurityGroupsRequest.builder()
                .groupIds(command.getSecurityGroupIds())
                .build();

        DescribeSecurityGroupsResponse describeSecurityGroupsResponse = ec2Client.describeSecurityGroups(describeSecurityGroupsRequest);
        List<software.amazon.awssdk.services.ec2.model.SecurityGroup> securityGroupList = describeSecurityGroupsResponse.securityGroups();
        for(software.amazon.awssdk.services.ec2.model.SecurityGroup securityGroup:securityGroupList){
            SecurityGroup securityGroupModel=new SecurityGroup();
            securityGroupModel.setDescription(securityGroup.description());
            securityGroupModel.setSecurityGroupId(securityGroup.groupId());
            securityGroupModel.setVpcId(securityGroup.vpcId());
            securityGroupModel.setName(securityGroup.groupName());
            List<IpPermission> ipPermissionList = securityGroup.ipPermissions();
            for(IpPermission ipPermission:ipPermissionList){
                InboundRule inboundRule=new InboundRule();
                inboundRule.setSecurityGroupId(securityGroup.groupId());
                inboundRule.setFromPort(ipPermission.fromPort());
                inboundRule.setToPort(ipPermission.toPort());
                inboundRule.setIpProtocol(ipPermission.ipProtocol());
                inboundRule.setIpRangeAllowedIn(ipPermission.ipRanges().get(0).cidrIp());
                inboundRules.add(inboundRule);

            }
            securityGroupModel.setInboundRules(inboundRules);

            securityGroups.add(securityGroupModel);
        }


        return securityGroups;
    }

    @Override
    public String startInstance( String instanceId ) {

        StartInstancesRequest request = StartInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        StartInstancesResponse startInstancesResponse = ec2Client.startInstances(request);

        return   startInstancesResponse.startingInstances().get(0).currentState().toString();
    }

    @Override
    public String stopInstance( String instanceId ) {

        StopInstancesRequest stopInstancesRequest=  StopInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        StopInstancesResponse stopInstancesResponse = ec2Client.stopInstances(stopInstancesRequest);
        return stopInstancesResponse.stoppingInstances().get(0).currentState().toString();
    }

    @Override
    public String terminateInstance( String instanceId ) {

        TerminateInstancesRequest terminateInstancesRequest = TerminateInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        TerminateInstancesResponse terminateInstancesResponse = ec2Client.terminateInstances(terminateInstancesRequest);
        return terminateInstancesResponse.terminatingInstances().get(0).currentState().toString();
    }

    @Override
    public Instance createInstance( CreateInstanceCommand command ) {
        KeyPair keyPair = command.getKeyPair();

        Tag tag = Tag.builder()
                .key( "Name" )
                .value( command.getInstanceName() )
                .build();

        TagSpecification tagSpecification = TagSpecification.builder()
                .tags( tag )
                .build();
        var runInstancesRequest = RunInstancesRequest
                .builder()
                .tagSpecifications( tagSpecification )
                .keyName( keyPair.getKeyName() )
                .imageId( command.getAmiId() )
                .instanceType( command.getInstanceType() )
                .subnetId( command.getSubnetId() )
                .maxCount( 1 ).minCount( 1 ).build();
        var runInstancesResponse = ec2Client.runInstances( runInstancesRequest );
        Instance instance = mapCreateInstanceProperties( runInstancesResponse, keyPair, tag );
        if ( !runInstancesResponse.hasInstances() )
            throw new AwsGatewayException("Failed to create instance.");

        return instance;
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

    @Override
    public Instance createInstance( TemplateConfiguration template, String instanceName ) {
        Tag tag = Tag.builder()
                .key( "Name" )
                .value( instanceName )
                .build();
        TagSpecification tagSpecification = TagSpecification.builder()
                .tags( tag )
                .build();
        var runInstancesRequest = RunInstancesRequest
                .builder()
                .imageId( template.getAmiId() )
                .tagSpecifications( tagSpecification )
                .keyName( template.getKeyPair().getKeyName() )
                .instanceType( template.getInstanceType() )
                .subnetId( template.getSubnetId() )
                .maxCount( 1 ).minCount( 1 ).build();
        var runInstancesResponse = ec2Client.runInstances( runInstancesRequest );
        Instance instance = mapCreateInstanceProperties( runInstancesResponse, template.getKeyPair(), tag );
        if ( !runInstancesResponse.hasInstances() )
            throw new AwsGatewayException("Failed to create instance.");
        return instance;
    }

    @Override
    public Optional<Instance> describeInstance( String instanceId ) {
        var request = DescribeInstancesRequest.builder().instanceIds( instanceId ).build();
        var response = ec2Client.describeInstances( request );
        if ( response.hasReservations() && response.reservations().get( 0 ).hasInstances() ) {

            var awsInstance = response.reservations().get( 0 ).instances().get( 0 );
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
    public List<Instance> describeInstances( List<String> instanceIds ) {
        var describeInstancesRequest = DescribeInstancesRequest.builder().instanceIds( instanceIds ).build();
        var describeInstancesResponse = this.ec2Client.describeInstances( describeInstancesRequest );
        return describeInstancesResponse.hasReservations() && describeInstancesResponse.reservations().get( 0 ).hasInstances()
                ? getMappedInstances( describeInstancesResponse )
                : new ArrayList<>();
    }

    private List<Instance> getMappedInstances( DescribeInstancesResponse describeInstancesResponse ) {
        return describeInstancesResponse.reservations().get( 0 ).instances().stream().map( this::mapDescribeInstanceProperties ).collect( Collectors.toList() );
    }

}
