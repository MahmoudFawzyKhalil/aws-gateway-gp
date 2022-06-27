package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TemplateConfigurationDaoImpl implements TemplateConfigurationDao {

    private TemplateConfigurationRepo templateConfigurationRepo;

    @Override
    public TemplateConfiguration save(TemplateConfiguration templateConfiguration) {
        return templateConfigurationRepo.save(templateConfiguration);
    }

    @Override
    public TemplateConfiguration update(TemplateConfiguration templateConfiguration) {
        if(templateConfiguration == null || templateConfiguration.getId() == null){
            throw new NullPointerException("template configuration or can't be null");
        }
        return templateConfigurationRepo.save(templateConfiguration);
    }

    @Override
    public Optional<TemplateConfiguration> findById(Integer id) {
        return templateConfigurationRepo.findById(id);
    }

    @Override
    public <C> Optional<C> findById(Integer id, Class<C> projection) {
        return templateConfigurationRepo.findById(id,projection);
    }

    @Override
    public List<TemplateConfiguration> findAll() {
        return templateConfigurationRepo.findAll();
    }

    @Override
    public List<TemplateConfiguration> findAll(int pageNumber, int pageSize) {
        Page<TemplateConfiguration> templateConfigurationPage = templateConfigurationRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return templateConfigurationPage.toList();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return templateConfigurationRepo.findBy(PageRequest.of(pageNumber,pageSize),projection).getContent();
    }

    @Override
    public List<TemplateConfiguration> findAllByExample(TemplateConfiguration example) {
        return templateConfigurationRepo.findAll(Example.of(example));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return templateConfigurationRepo.findAllBy(Example.of(example,caseInsensitiveExampleMatcher),projection);
    }

    @Override
    public <T> List<T> findAllByInstructor(int id, Class<T> projection) {
        return templateConfigurationRepo.findAllByInstructors_id(id,projection);
    }

    @Override
    public <T> List<T> findAllByInstructor(String userName, Class<T> projection) {
        return templateConfigurationRepo.findAllByInstructors_username(userName,projection);
    }


    @Override
    public <T> List<T> findAllTemplateByCreatorId(int id, Class<T> projection) {
        return templateConfigurationRepo.findAllByCreator_id(id,projection);
    }
}