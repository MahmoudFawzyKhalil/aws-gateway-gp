package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.dao.TemplateConfigurationDao;
import eg.gov.iti.jets.persistence.entity.aws.TemplateConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TemplateConfigurationDaoImpl implements TemplateConfigurationDao {

    private final TemplateConfigurationRepo templateConfigurationRepo;

    public TemplateConfigurationDaoImpl(TemplateConfigurationRepo templateConfigurationRepo){
        this.templateConfigurationRepo=templateConfigurationRepo;
    }

    @Override
    public TemplateConfiguration save(TemplateConfiguration templateConfiguration) {
        return templateConfigurationRepo.save(templateConfiguration);
    }

    @Override
    public TemplateConfiguration update(TemplateConfiguration templateConfiguration) {
        return templateConfigurationRepo.save(templateConfiguration);
    }

    @Override
    public Optional<TemplateConfiguration> findById(Integer id) {
        return templateConfigurationRepo.findById(id);
    }

    @Override
    public <C> Optional<TemplateConfiguration> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<TemplateConfiguration> findAll() {
        return templateConfigurationRepo.findAll();
    }

    @Override
    public List<TemplateConfiguration> findAll(int pageNumber, int pageSize) {
        Page<TemplateConfiguration> templateConfigurationPage = templateConfigurationRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return templateConfigurationPage.getContent();
    }

    @Override
    public <C> List<TemplateConfiguration> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<TemplateConfiguration> findAllByExample(TemplateConfiguration example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return templateConfigurationRepo.findAll(Example.of(example,caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<TemplateConfiguration> findAllByExample(TemplateConfiguration example, Class<C> projection) {
        return null;
    }
}
