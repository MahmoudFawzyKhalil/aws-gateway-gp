package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.template.TemplateResponse;
import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TemplateManagementImpl implements TemplateManagement {
    final
    SecurityGroupDao securityGroupDao;
    final
    TemplateConfigurationDao templateConfigurationDao;
    final
    AwsGateway awsGateway;
    final
    UserDao userDao;

    public TemplateManagementImpl( TemplateConfigurationDao templateConfigurationDao, AwsGateway awsGateway, SecurityGroupDao securityGroupDao, UserDao userDao ) {
        this.templateConfigurationDao = templateConfigurationDao;
        this.awsGateway = awsGateway;
        this.securityGroupDao = securityGroupDao;
        this.userDao = userDao;
    }

    public Boolean deleteTemplate( int id ) {

        return null;
    }


    public List<TemplateConfiguration> getTemplateConfiguration() {
        return templateConfigurationDao.findAll();
    }

    @Override
    public List<TemplateResponse> getTemplateConfigurationById( int id ) {
        Optional<User> user = userDao.findById( id );
        if(user.isPresent()){
            String role = user.get().getRole().getName();
            switch ( role ){
                case "INSTRUCTOR":
                    return getTemplateResponses( id );
                case "TRACK_SUPERVISOR":
                    return getTemplateResponseList( id );
                default:
                    throw new IllegalStateException( "Unexpected value: " + role );
            }
        }else {
            throw new IllegalStateException( "Unexpected value: " );
        }
    }

    private List<TemplateResponse> getTemplateResponses( int id ) {
        List<TemplateResponse> allByInstructor = templateConfigurationDao.findAllByInstructor( id, TemplateResponse.class );
        return allByInstructor;
    }

    private List<TemplateResponse> getTemplateResponseList( int id ) {
        List<TemplateResponse> allTemplateCreatedBySuperVisor = templateConfigurationDao.findAllTemplateByCreatorId( id, TemplateResponse.class );
        return allTemplateCreatedBySuperVisor;
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
