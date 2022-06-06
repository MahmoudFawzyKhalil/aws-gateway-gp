package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.entity.Intake;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IntakeDaoImpl implements IntakeDao {
    private final IntakeRepo intakeRepo;

    public IntakeDaoImpl(IntakeRepo intakeRepo){
        this.intakeRepo = intakeRepo;
    }
    @Override
    public Intake save(Intake entity) {
        return intakeRepo.save(entity);
    }

    @Override
    public Intake update(Intake entity) {
        return intakeRepo.save(entity);
    }

    @Override
    public Optional<Intake> findById(Integer integer) {
            return intakeRepo.findById(integer);
    }

    @Override
    public <C> Optional<C> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<Intake> findAll() {
        return intakeRepo.findAll();
    }

    @Override
    public List<Intake> findAll(int pageNumber, int pageSize) {
        Page<Intake> intakePage = intakeRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return intakePage.getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<Intake> findAllByExample(Intake example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return intakeRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        return null;
    }
}
