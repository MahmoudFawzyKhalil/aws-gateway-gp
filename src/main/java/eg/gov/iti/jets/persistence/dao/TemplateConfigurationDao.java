package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;

import java.util.List;

public interface TemplateConfigurationDao extends GenericCrudDao<TemplateConfiguration,Integer> {
    <T> List<T> findAllByInstructor(int id, Class<T> projection);

    <T> List<T> findAllByInstructor(String userName, Class<T> projection);
}
