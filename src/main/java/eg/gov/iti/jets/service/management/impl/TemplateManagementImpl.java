package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import eg.gov.iti.jets.service.management.TemplateManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateManagementImpl implements TemplateManagement {
    
    final
    TemplateConfigurationDao templateConfigurationDao;

    public TemplateManagementImpl( TemplateConfigurationDao templateConfigurationDao ) {
        this.templateConfigurationDao = templateConfigurationDao;
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


    //TODO what do you really want

}
