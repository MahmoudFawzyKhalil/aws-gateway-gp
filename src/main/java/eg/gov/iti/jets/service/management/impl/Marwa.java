package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Marwa {

    @Autowired
    TemplateConfigurationDao templateConfigurationDao;
    List<TemplateConfiguration> getTemplateConfiguration(){
        return templateConfigurationDao.findAll();
    }




}
