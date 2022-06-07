package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.management.TrainingProgramManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingProgramManagementImpl implements TrainingProgramManagement {

    @Autowired
    private TrainingProgramDao trainingProgramDao;

    @Override
    public Boolean createTrainingProgram(TrainingProgram trainingProgram) {
        if(trainingProgramDao.save(trainingProgram) != null)
        return true;
        else
            return false;
    }

    @Override
    public TrainingProgram updateTrainingProgram(TrainingProgram trainingProgram) {
        return trainingProgramDao.save(trainingProgram);
    }

    @Override
    public Boolean deleteTrainingProgram(int id) {
        return null;
    }

    @Override
    public List<TrainingProgram> getAllTrainingPrograms() {

        return trainingProgramDao.findAll();
    }

    @Override
    public TrainingProgram getTrainingProgramById(int id) {
        Optional<TrainingProgram> trainingProgram = trainingProgramDao.findById(id);
        return trainingProgram.orElse(null);
    }
}

