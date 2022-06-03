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
public class TemplateConfigurationImpl implements TemplateConfigurationDao {

    private TemplateConfigurationRepo templateConfigurationRepo;

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
    public List<TemplateConfiguration> findAll() {
        return templateConfigurationRepo.findAll();
    }

    @Override
    public List<TemplateConfiguration> findAll(int pageNumber, int pageSize) {
        Page<TemplateConfiguration> templateConfigurationPage = templateConfigurationRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return templateConfigurationPage.toList();
    }

    @Override
    public List<TemplateConfiguration> findAllByExample(TemplateConfiguration example) {
        return templateConfigurationRepo.findAll(Example.of(example));
    }
}
