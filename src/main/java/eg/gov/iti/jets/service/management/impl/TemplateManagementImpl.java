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
    final
    SecurityGroupDao securityGroupDao;
    final
    TemplateConfigurationDao templateConfigurationDao;
    final
    AwsGateway awsGateway;

    public TemplateManagementImpl( TemplateConfigurationDao templateConfigurationDao, AwsGateway awsGateway, SecurityGroupDao securityGroupDao ) {
        this.templateConfigurationDao = templateConfigurationDao;
        this.awsGateway = awsGateway;
        this.securityGroupDao = securityGroupDao;
    }

    public Boolean deleteTemplate( int id ) {

        return null;
    }


    public List<TemplateConfiguration> getTemplateConfiguration() {
        return templateConfigurationDao.findAll();
    }

    @Override
    public List<TemplateConfiguration> getTemplateConfigurationById( int id ) {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();

        templateConfiguration.setInstructors( new ArrayList<>(id) );

        return templateConfigurationDao.findAllByExample( templateConfiguration );

    }

    @Transactional
    @Override
    public Boolean createTemplate( TemplateConfiguration templateConfiguration ) {
        List<SecurityGroup> securityGroups = saveSecurityGroup( templateConfiguration.getSecurityGroups() );
        templateConfiguration.setSecurityGroups( securityGroups );
        TemplateConfiguration templateConfigurationAfterSaving = templateConfigurationDao.save( templateConfiguration );
        return templateConfigurationAfterSaving != null;
    }

    @Transactional
    List<SecurityGroup> saveSecurityGroup( List<SecurityGroup> securityGroups ) {
        List<SecurityGroup> securityGroupList = new ArrayList<>();
        for ( SecurityGroup securityGroup : securityGroups ) {
            List<SecurityGroup> securityGroupFromDao = securityGroupDao.findAllByExample( securityGroup );
            if ( securityGroupFromDao.isEmpty() ) {
                securityGroupList.add( securityGroupDao.save( securityGroup ) );
            } else {
                securityGroupList.add( securityGroupFromDao.get( 0 ) );
            }

        }
        return securityGroupList;
    }


}
