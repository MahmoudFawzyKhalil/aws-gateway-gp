package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.api.resource.template.TemplateAssignRequest;
import eg.gov.iti.jets.api.resource.template.TemplateResponse;
import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.exception.AwsGatewayException;
import eg.gov.iti.jets.service.exception.ResourceAlreadyExistException;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.TemplateManagement;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
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
    UserDao userDao;
    final
    MapperUtilForApi mapperUtilForApi;
    @Autowired
    AwsGateway awsGateway;

    public TemplateManagementImpl( SecurityGroupDao securityGroupDao, TemplateConfigurationDao templateConfigurationDao, UserDao userDao, MapperUtilForApi mapperUtilForApi ) {
        this.securityGroupDao = securityGroupDao;
        this.templateConfigurationDao = templateConfigurationDao;
        this.userDao = userDao;
        this.mapperUtilForApi = mapperUtilForApi;
    }


    public Boolean deleteTemplate( int id ) {

        return null;
    }

    @Override
    public void assignTemplates( TemplateAssignRequest templateAssignRequest ) {

        List<TemplateConfiguration> configurations = mapperUtilForApi.getTemplateConfigurationsByIds( templateAssignRequest.getTemplateConfigurationIds() );
        List<User> instructors = mapperUtilForApi.getUsers( templateAssignRequest.getInstructorIds() );
        configurations.forEach( c -> {c.setInstructors( instructors ); templateConfigurationDao.update( c );} );
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
                    throw new ResourceNotFoundException( "Unexpected value: " + role );
            }
        }else {
            throw new ResourceNotFoundException( "Unexpected value: " );
        }
    }

    private List<TemplateResponse> getTemplateResponses( int id ) {
        return templateConfigurationDao.findAllByInstructor( id, TemplateResponse.class );
    }

    private List<TemplateResponse> getTemplateResponseList( int id ) {
        return templateConfigurationDao.findAllTemplateByCreatorId( id, TemplateResponse.class );
    }

    @Transactional
    @Override
    public void createTemplate( TemplateConfiguration templateConfiguration ) {
        try {
            Optional<Ami> ami = awsGateway.describeAmi( templateConfiguration.getAmiId() );
            List<SecurityGroup> securityGroups = saveSecurityGroup( templateConfiguration.getSecurityGroups() );
            templateConfiguration.setSecurityGroups( securityGroups );
            TemplateConfiguration templateConfigurationAfterSaving = templateConfigurationDao.save( templateConfiguration );

        }catch (Exception e) {
            throw new AwsGatewayException("There is no AMI-ID like this "+templateConfiguration.getAmiId());
        }
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
