package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Role;
import org.springframework.data.domain.Example;
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
    public List<Intake> findAll() {
        return intakeRepo.findAll();
    }

    @Override
    public List<Intake> findAll(int pageNumber, int pageSize) {
        return (List<Intake>) intakeRepo.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    public List<Intake> findAllByExample(Intake example) {
        return intakeRepo.findAll(Example.of(example));
    }
}
