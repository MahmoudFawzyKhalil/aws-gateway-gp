package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.InstanceManagement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstanceManagementImpl implements InstanceManagement {

    private final InstanceDao instanceDao;
    private final UserDao userDao;
    private final AwsGateway awsGateway;
    private static final long DEFAULT_INSTANCE_TTL = 60L;


    public InstanceManagementImpl(InstanceDao instanceDao, UserDao userDao, AwsGateway awsGateway) {
        this.instanceDao = instanceDao;
        this.userDao = userDao;
        this.awsGateway = awsGateway;
    }

    @Override
    @Transactional
    public Instance createInstance(Instance instanceToCreate) {
        setDefaultTimeToLive(instanceToCreate);

        Instance createdInstance = awsGateway.createInstance(
                instanceToCreate.getTemplateConfiguration(),
                instanceToCreate.getName(),
                instanceToCreate.getKeyPair(),
                instanceToCreate.getTimeToLiveInMinutes());

        System.out.println(createdInstance.getInstanceId());

        createdInstance.setInstanceUsers(instanceToCreate.getInstanceUsers());
        createdInstance.setCreator(instanceToCreate.getCreator());
        createdInstance.setTemplateConfiguration(instanceToCreate.getTemplateConfiguration());
        createdInstance.setCreationDateTime(LocalDateTime.now());
        return instanceDao.save(createdInstance);
    }

    private void setDefaultTimeToLive(Instance instanceToCreate) {
        if (instanceToCreate.getTimeToLiveInMinutes() == null) {
            instanceToCreate.setTimeToLiveInMinutes(DEFAULT_INSTANCE_TTL);
        }
    }

    @Override
    public String startInstance(String instanceId) {
        Instance example = new Instance();
        example.setInstanceId(instanceId);

        var result = instanceDao.findAllByExample(example);
        if (result.isEmpty())
            throw new IllegalArgumentException(String.format("No instance exists with the id [%s]", instanceId));

        Instance instance = result.get(0);
        String instanceState = awsGateway.startInstance(instance);
        instance.setState(instanceState);
        instanceDao.update(instance);
        return instanceState;
    }

    @Override
    public String stopInstance(String instanceId) {
        Instance example = new Instance();
        example.setInstanceId(instanceId);

        var result = instanceDao.findAllByExample(example);
        if (result.isEmpty())
            throw new IllegalArgumentException(String.format("No instance exists with the id [%s]", instanceId));

        Instance instance = result.get(0);
        String instanceState = awsGateway.stopInstance(instance.getInstanceId());
        instance.setState(instanceState);
        instanceDao.update(instance);
        return instanceState;
    }

    @Override
    public String deleteInstance(String instanceId) {
        Instance example = new Instance();
        example.setInstanceId(instanceId);

        var result = instanceDao.findAllByExample(example);
        if (result.isEmpty())
            throw new IllegalArgumentException(String.format("No instance exists with the id [%s]", instanceId));

        Instance instance = result.get(0);
        String instanceState = awsGateway.terminateInstance(instance.getInstanceId());
        instance.setState("terminated");
        instanceDao.update(instance);
        return instanceState;
    }

    @Override
    public Instance getInstanceDetails(String instanceId) {
        System.out.println("heyyyyyy  "+instanceId);
        Instance example = new Instance();
        example.setInstanceId(instanceId);

        System.out.println(example);

        List<Instance> result = instanceDao.findAllByExample(example);
        System.out.println(result);
        if (result.isEmpty())
            throw new IllegalArgumentException(String.format("No instance exists with the id [%s]", instanceId));
        Instance instance = result.get(0);

        awsGateway.updateInstanceInfoFromAws(instance);
        return instance;
    }

    @Override
    public List<Instance> getInstancesByUserId(Integer id) {

        Optional<User> optionalUser = userDao.findById(id);
//
//        instanceDao.findUserGrantedInstances(  )
//
//        awsGateway.updateInstancesInfoFromAws(grantedNonTerminatedInstances);

        return null;
    }

}
