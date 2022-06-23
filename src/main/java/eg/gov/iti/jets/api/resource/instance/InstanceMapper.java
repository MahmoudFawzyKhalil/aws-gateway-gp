package eg.gov.iti.jets.api.resource.instance;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.util.MapperUtilForApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceMapper {
    private final
    MapperUtilForApi mapperUtilForApi;

    public InstanceMapper( MapperUtilForApi mapperUtilForApi ) {
        this.mapperUtilForApi = mapperUtilForApi;
    }

    public Instance mapFromInstanceReqToInstance( InstanceRequest instanceRequest , int studentId, int creatorId ) {
        Instance instance = new Instance();
        instance.setTemplateConfiguration( mapperUtilForApi.getTemplateConfigurationById( instanceRequest.getTemplateId() ) );
        instance.setKeyPair( mapperUtilForApi.getKeyPair( instanceRequest.getKeyPair(), creatorId ) );
        instance.setName( instanceRequest.getInstanceName() );
        instance.setInstanceUsers( mapperUtilForApi.getUser( studentId ) );
        instance.setCreator( mapperUtilForApi.getUser( creatorId ) );
        instance.setTimeToLiveInMinutes(instanceRequest.getTimeToLiveInMinutes());
        return instance;
    }

    public InstanceResponse mapFromInstanceToInstanceResponse( Instance instance ) {
        InstanceResponse instanceResponse = new InstanceResponse();
        instanceResponse.setInstanceId( instance.getInstanceId() );
        instanceResponse.setInstanceType( instance.getInstanceType() );
        instanceResponse.setCreator( mapFromUserToUserResponse( instance.getCreator() ) );
        instanceResponse.setId( instance.getId() );
        instanceResponse.setName( instance.getName() );
        instanceResponse.setVpcId( instance.getVpcId() );
        instanceResponse.setSubnetId( instance.getSubnetId() );
        instanceResponse.setAmiId( instance.getAmiId() );
        instanceResponse.setKeyPair( instance.getKeyPair().getKeyMaterial() );
        instanceResponse.setPlatform( instance.getPlatform() );
        instanceResponse.setCreationDateTime( instance.getCreationDateTime().toString() );
        instanceResponse.setDecryptedPassword( instance.getDecryptedPassword() );
        instanceResponse.setTemplateConfiguration( mapFromTemplateToTemplateResponse( instance.getTemplateConfiguration() ) );
        instanceResponse.setPublicDnsName( instance.getPublicDnsName() );
        instanceResponse.setUsername( instance.getUsername() );
        instanceResponse.setState( instance.getState() );
        instanceResponse.setTimeToLiveInMinutes( instance.getTimeToLiveInMinutes() );
        return instanceResponse;
    }

    private TemplateConfigurationInstanceResponse mapFromTemplateToTemplateResponse( TemplateConfiguration templateConfiguration){
        TemplateConfigurationInstanceResponse templateConfigurationInstanceResponse = new TemplateConfigurationInstanceResponse();
        templateConfigurationInstanceResponse.setInstanceType( templateConfiguration.getInstanceType() );
        templateConfigurationInstanceResponse.setAmiId( templateConfiguration.getAmiId() );
        templateConfigurationInstanceResponse.setSubnetId( templateConfiguration.getSubnetId() );
        templateConfigurationInstanceResponse.setId( templateConfiguration.getId() );
        return templateConfigurationInstanceResponse;
    }

    private UserInstanceResponse mapFromUserToUserResponse( User user ){
        UserInstanceResponse userInstanceResponse = new UserInstanceResponse();
        userInstanceResponse.setEmail( user.getEmail() );
        userInstanceResponse.setUsername( user.getUsername() );
        userInstanceResponse.setId( user.getId() );
        userInstanceResponse.setPassword( user.getPassword() );
        return userInstanceResponse;
    }
}
