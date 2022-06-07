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
    Instance createInstanceAws(TemplateConfiguration templateConfiguration, String instanceName , KeyPair keyPair ,User user , List<SecurityGroup> sg) {
        Instance instance = awsGateway.createInstance( templateConfiguration, instanceName, keyPair );
        instance.setTemplateConfiguration( templateConfiguration );
        keyPair.setCreator( user ) ;
        instance.setCreator( user );
        keyPairDao.save( keyPair );
        instanceDao.save( instance );
//        instance.setSecurityGroups(


        return awsGateway.createInstance( templateConfiguration , instanceName, keyPair );
    }

    @Transactional
    @Override
    public Optional<Instance> createInstance( int templateConfigurationId, String instanceName , String keyPair , User user){
        Optional<TemplateConfiguration> byId = templateConfigurationDao.findById( templateConfigurationId );
        KeyPair keyPair1 = awsGateway.createKeyPair( keyPair );
        return Optional.ofNullable( byId.map( templateConfiguration -> createInstanceAws( templateConfiguration, instanceName, keyPair1 ,user ,templateConfiguration.getSecurityGroups()) ).orElse( null ) );

    }

    @Override
    public Boolean startInstance( String instanceId ) {
        return null;
    }

    @Override
    public Boolean stopInstance( String instanceId ) {
        return null;
    }

    @Override
    public Boolean deleteInstance( String instanceId ) {
        return null;
    }

}
