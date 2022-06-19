package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateManagementImpl implements TemplateManagement {

    @Autowired
    SecurityGroupDao securityGroupDao;

    final
    TemplateConfigurationDao templateConfigurationDao;


    private final AwsGateway awsGateway;

    public TemplateManagementImpl(TemplateConfigurationDao templateConfigurationDao, AwsGateway awsGateway) {
        this.templateConfigurationDao = templateConfigurationDao;
        this.awsGateway = awsGateway;
    }

    public Boolean deleteTemplate ( int id ){

        return null;
    }


    public List<TemplateConfiguration> getTemplateConfiguration(){
        return templateConfigurationDao.findAll();
    }

    @Transactional
    @Override
    public Boolean createTemplate(TemplateConfiguration templateConfiguration) {

        List<SecurityGroup> saved = new ArrayList<>();
        for ( SecurityGroup s: templateConfiguration.getSecurityGroups() ) {
            List<SecurityGroup> securityGroupDaoAllByExample = securityGroupDao. findAllByExample( s );
            if(securityGroupDaoAllByExample.isEmpty()){
                saved.add( securityGroupDao.save( s ) );
            }else{
                saved.add( securityGroupDaoAllByExample.get( 0 ) );
            }
        }
        templateConfiguration.setSecurityGroups( saved );

        TemplateConfiguration net= templateConfigurationDao.save(templateConfiguration);
        return net != null;
    }


}
