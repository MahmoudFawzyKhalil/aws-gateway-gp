package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.Subnet;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateManagementImpl implements TemplateManagement {
    
    final
    TemplateConfigurationDao templateConfigurationDao;

    private final AwsGateway awsGateway;

    public TemplateManagementImpl(TemplateConfigurationDao templateConfigurationDao, AwsGateway awsGateway) {
        this.templateConfigurationDao = templateConfigurationDao;
        this.awsGateway = awsGateway;
    }

    @Override
    public List<String> getInstanceTypes(){
        return awsGateway.getInstanceTypes();
    }


    @Override
    public Optional<Ami> describeAmi( String amiId){
        return awsGateway.describeAmi(amiId);
    }

    public Boolean deleteTemplate ( int id ){
        return null;
    }

    // TODO: 6/5/2022 get template upon condition after user 
    public List<TemplateConfiguration> getTemplateConfiguration(){
        return templateConfigurationDao.findAll();
    }

    @Override
    public Boolean createTemplate(TemplateConfiguration templateConfiguration) {
        TemplateConfiguration net= templateConfigurationDao.save(templateConfiguration);
        if(net==null){
            return false;
        }
        else{
        return true;}
    }
    @Override
    public List<Subnet> getAllSubnet(){
        return awsGateway.describeAllSubnets();
    }

    @Override
    public List<SecurityGroup> describeSecurityGroupsForVpc(String vpcId){
        return awsGateway.describeSecurityGroupsForVpc(vpcId);
    }

    //TODO what do you really want

}
