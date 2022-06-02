package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
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
    public List<TrainingProgram> findAll() {
        return trainingProgramRepo.findAll();
    }

    @Override
    public List<TrainingProgram> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<TrainingProgram> findAllByExample(TrainingProgram example) {
        return null;
    }


}
