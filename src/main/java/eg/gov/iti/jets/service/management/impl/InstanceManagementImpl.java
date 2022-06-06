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





    private Instance createInstanceAws(TemplateConfiguration templateConfiguration, String instanceName , KeyPair keyPair) {
        return awsGateway.createInstance( templateConfiguration , instanceName, keyPair );
    }

    @Transactional
    @Override
    public Optional<Instance> createInstance( int templateConfigurationId, String instanceName , String keyPair){
        Optional<TemplateConfiguration> byId = templateConfigurationDao.findById( templateConfigurationId );
        KeyPair keyPair1 = awsGateway.createKeyPair( keyPair );
        Optional<Instance> instance = Optional.ofNullable( byId.map( templateConfiguration -> createInstanceAws( templateConfiguration, instanceName, keyPair1 ) ).orElse( null ) );
        if(instance.isPresent()){
            Instance instance1 = instance.get();
//            User user = userDao.findById( 1 ).get();
//            instance1.setTemplateConfiguration( byId.get() );
//            System.out.println( user );
//            instance1.setCreator( user );
//            System.out.println(instance1);
//            keyPairDao.save( keyPair1 );
//            instanceDao.save( instance1 );

            return instance;
        }else {
            return null;
        }
    }

}
