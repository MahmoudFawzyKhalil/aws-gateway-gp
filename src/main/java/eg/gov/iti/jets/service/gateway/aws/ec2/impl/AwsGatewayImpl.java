package eg.gov.iti.jets.service.gateway.aws.ec2.impl;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.Vpc;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.model.*;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AwsGatewayImpl implements AwsGateway {


    private final Ec2Client ec2Client;

    public AwsGatewayImpl(Ec2Client ec2Client) {
        this.ec2Client = ec2Client;
    }

    @Override
    public List<Vpc> describeVpcs(DescribeVpcsCommand command) {
        return null;
    }

    @Override
    public List<Subnet> describeSubnets(DescribeSubnetsCommand command) {
        return null;
    }

    @Override
    public KeyPair createKeyPair(CreateKeyPairCommand command) {
        var createKeyPairRequest = CreateKeyPairRequest.builder().keyName(command.getName()).build();
        var keyPairResponse = ec2Client.createKeyPair(createKeyPairRequest);
        KeyPair keyPair = new KeyPair();
        keyPair.setKeyPairId(keyPairResponse.keyPairId());
        keyPair.setKeyName(keyPairResponse.keyName());
        keyPair.setKeyMaterial(keyPairResponse.keyMaterial());
        return keyPair;
    }

    @Override
    public List<SecurityGroup> describeSecurityGroups(DescribeSecurityGroupsCommand command) {
        return null;
    }

    @Override
    public String startInstance(String instanceId) {
        return null;
    }

    @Override
    public String stopInstance(String instanceId) {
        return null;
    }

    @Override
    public String terminateInstance(String instanceId) {
        return null;
    }

    @Override
    public Optional<Instance> createInstance(CreateInstanceCommand command) {
        KeyPair keyPair = command.getKeyPair();
        var runInstancesRequest = RunInstancesRequest
                .builder()
                .keyName(keyPair.getKeyName())
                .imageId(command.getAmiId())
                .instanceType(command.getInstanceType())
                .subnetId(command.getSubnetId())
                .maxCount(1).minCount(1).build();
        var runInstancesResponse = ec2Client.runInstances(runInstancesRequest);
        Tag tag = createTag(command.getTagName(), ec2Client, runInstancesResponse.instances().get(0).instanceId());
        var instance = mapCreateInstanceProperties(runInstancesResponse, keyPair, tag);
        return Optional.of(instance);

    }


    private Instance mapCreateInstanceProperties(RunInstancesResponse runInstancesResponse, KeyPair keyPair, Tag tag) {
        Instance instance = new Instance();
        instance.setInstanceId(runInstancesResponse.instances().get(0).instanceId());
        instance.setKeyPair(keyPair);
        instance.setInstanceType(runInstancesResponse.instances().get(0).instanceTypeAsString());
        instance.setVpcId(runInstancesResponse.instances().get(0).vpcId());
        instance.setAmiId(runInstancesResponse.instances().get(0).imageId());
        instance.setName(tag.value());
        instance.setSubnetId(runInstancesResponse.instances().get(0).subnetId());
        return instance;
    }

    private Tag createTag(String name, Ec2Client ec2Client, String instanceId) {
        Tag tag = Tag.builder().key("Name").value(name).build();
        var tagRequest = CreateTagsRequest.builder().resources(instanceId).tags(tag).build();
        ec2Client.createTags(tagRequest);
        return tag;
    }

    @Override
    public Optional<Instance> createInstance(TemplateConfiguration template, String instanceName) {
        var runInstancesRequest = RunInstancesRequest
                .builder()
                .imageId(template.getAmiId())
                .keyName(template.getKeyPair().getKeyName())
                .instanceType(template.getInstanceType())
                .subnetId(template.getSubnetId())
                .maxCount(1).minCount(1).build();

        var runInstancesResponse = ec2Client.runInstances(runInstancesRequest);
        Tag tag = createTag(instanceName, ec2Client, runInstancesResponse.instances().get(0).instanceId());
        Instance instance = mapCreateInstanceProperties(runInstancesResponse, template.getKeyPair(), tag);

        return runInstancesResponse.hasInstances() ? Optional.of(instance) : Optional.empty();

    }

    @Override
    public Optional<Instance> describeInstance(String instanceId) {
        var request = DescribeInstancesRequest.builder().instanceIds(instanceId).build();
        var response = ec2Client.describeInstances(request);
        if (response.hasReservations() && response.reservations().get(0).hasInstances()) {

            software.amazon.awssdk.services.ec2.model.Instance awsInstance = response.reservations().get(0).instances().get(0);
            return Optional.of(mapDescribeInstanceProperties(awsInstance));
        }
        return Optional.empty();
    }

    private Instance mapDescribeInstanceProperties(software.amazon.awssdk.services.ec2.model.Instance awsInstance) {
        Instance instance = new Instance();
        instance.setInstanceId(awsInstance.instanceId());
        instance.setAmiId(awsInstance.imageId());
        instance.setInstanceType(awsInstance.instanceTypeAsString());
        instance.setState(awsInstance.state().nameAsString());
        instance.setPublicIp(awsInstance.publicIpAddress());
        return instance;
    }

    @Override
    public List<Instance> describeInstances(List<String> instanceIds) {
        var describeInstancesRequest = DescribeInstancesRequest.builder().instanceIds(instanceIds).build();
        var describeInstancesResponse = this.ec2Client.describeInstances(describeInstancesRequest);
        return describeInstancesResponse.hasReservations() && describeInstancesResponse.reservations().get(0).hasInstances()
                ? getMappedInstances(describeInstancesResponse)
                : new ArrayList<>();
    }

    private List<Instance> getMappedInstances(DescribeInstancesResponse describeInstancesResponse) {
        return describeInstancesResponse.reservations().get(0).instances().stream().map(this::mapDescribeInstanceProperties).collect(Collectors.toList());
    }
}
