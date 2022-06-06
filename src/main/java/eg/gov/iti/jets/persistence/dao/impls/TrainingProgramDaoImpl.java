package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrainingProgramDaoImpl implements TrainingProgramDao {

    private final TrainingProgramRepo trainingProgramRepo;

    public TrainingProgramDaoImpl(TrainingProgramRepo trainingProgramRepo){
        this.trainingProgramRepo=trainingProgramRepo;
    }

    @Override
    public TrainingProgram save(TrainingProgram trainingProgram) {
        return trainingProgramRepo.save(trainingProgram);
    }

    @Override
    public TrainingProgram update(TrainingProgram entity) {
        return trainingProgramRepo.save(entity);
    }

    @Override
    public Optional<TrainingProgram> findById(Integer integer) {
        return trainingProgramRepo.findById(integer);
    }

    @Override
    public <C> Optional<TrainingProgram> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<TrainingProgram> findAll() {
        return trainingProgramRepo.findAll();
    }


    @Override
    public List<TrainingProgram> findAll(int pageNumber, int pageSize) {
        Page<TrainingProgram> instancePage = trainingProgramRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return instancePage.toList();
    }

    @Override
    public <C> List<TrainingProgram> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<TrainingProgram> findAllByExample(TrainingProgram example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return trainingProgramRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<TrainingProgram> findAllByExample(TrainingProgram example, Class<C> projection) {
        return null;
    }


}
