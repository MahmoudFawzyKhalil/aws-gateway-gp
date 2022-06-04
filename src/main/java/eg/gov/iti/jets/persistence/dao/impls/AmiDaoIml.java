package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.AmiDao;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public class AmiDaoIml implements AmiDao {

    private final AmiRepo amiRepo;

    public AmiDaoIml(AmiRepo amiRepo) {
        this.amiRepo = amiRepo;
    }

    @Override
    public Ami save(Ami entity) {
        return amiRepo.save(entity);
    }

    @Override
    public Ami update(Ami entity) {
        return amiRepo.save(entity);
    }

    @Override
    public Optional<Ami> findById(Integer integer) {
        return amiRepo.findById(integer);
    }

    @Override
    public List<Ami> findAll() {
        return amiRepo.findAll();
    }

    @Override
    public List<Ami> findAll(int pageNumber, int pageSize) {
        Page<Ami> amiPage = amiRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return amiPage.getContent();
    }

    @Override
    public List<Ami> findAllByExample(Ami example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return amiRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }
}
