package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.KeyPairDao;
import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.InstanceManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InstanceManagementImpl implements InstanceManagement {

    @Autowired
    InstanceDao instanceDao;
    @Autowired
    KeyPairDao keyPairDao;
    @Autowired
    UserDao userDao;
    public final AwsGateway awsGateway;
    public final TemplateConfigurationDao templateConfigurationDao;


    public InstanceManagementImpl( AwsGateway awsGateway, TemplateConfigurationDao templateConfigurationDao ) {
        this.awsGateway = awsGateway;
        this.templateConfigurationDao = templateConfigurationDao;
    }






    @Transactional
    @Override
    public Instance createInstance(Instance instance){
        Instance instance1 = awsGateway.createInstance( instance.getTemplateConfiguration(), instance.getName(), instance.getKeyPair() );
        instance1.setInstanceUsers( instance.getInstanceUsers() );
        instance1.setCreator( instance.getCreator() );
        instance1.setTemplateConfiguration( instance.getTemplateConfiguration() );
        instance1.setCreationDateTime( LocalDateTime.now() );
        instance1.setState( "Running" );
        Instance saved = instanceDao.save( instance1 );
        return saved;
    }

    @Override
    public String startInstance( String instanceId ) {
        String s = awsGateway.startInstance( instanceId );
        Instance instance = new Instance();
        instance.setInstanceId( instanceId );
        Instance instance1 = instanceDao.findAllByExample( instance ).get( 0 );
        instance1.setState( "Running" );
        instanceDao.update( instance1 );
        return s;
    }

    @Override
    public String stopInstance( String instanceId ) {
        String s = awsGateway.stopInstance( instanceId );
        Instance instance = new Instance();
        instance.setInstanceId( instanceId );
        Instance instance1 = instanceDao.findAllByExample( instance ).get( 0 );
        instance1.setState( "Stopped" );
        instanceDao.update( instance1 );
        return s;
    }

    @Override
    public String deleteInstance( String instanceId ) {
        String s = awsGateway.terminateInstance( instanceId );
        Instance instance = new Instance();
        instance.setInstanceId( instanceId );
        Instance instance1 = instanceDao.findAllByExample( instance ).get( 0 );
        instance1.setState( "Terminated" );
        instanceDao.update( instance1 );
        return s;
    }

    @Override
    public Instance instanceDetails( String instanceId ) {
        Instance instance = new Instance();
        instance.setInstanceId( instanceId );
        Instance instance1 = instanceDao.findAllByExample( instance ).get( 0 );
        awsGateway.updateInstanceInfoFromAws( instance1 );

        // TODO: 6/7/2022 void where is the update
        return instance1;
    }

}
