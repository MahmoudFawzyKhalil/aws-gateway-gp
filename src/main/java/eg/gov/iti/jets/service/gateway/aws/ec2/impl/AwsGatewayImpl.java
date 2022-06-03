package eg.gov.iti.jets.service.gateway.aws.ec2.impl;

import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.model.*;

import java.util.List;

public class AwsGatewayImpl implements AwsGateway {
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
        return null;
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
    public Instance createInstance(CreateInstanceCommand command) {
        return null;
    }

    @Override
    public Instance createInstance(TemplateConfiguration template) {
        return null;
    }

    @Override
    public Instance describeInstance(String instanceId) {
        return null;
    }

    @Override
    public List<Instance> describeInstances(DescribeInstancesCommand command) {
        return null;
    }
}
