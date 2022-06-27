package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.TrainingProgramManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingProgramManagementImpl implements TrainingProgramManagement {

    @Autowired
    private TrainingProgramDao trainingProgramDao;
    @Autowired
    BranchDao branchDao;
    @Autowired
    IntakeDao intakeDao;

    @Override
    public Boolean createTrainingProgram(TrainingProgram trainingProgram) {
        if(trainingProgramDao.save(trainingProgram) != null)
        return true;
        else
            return false;
    }

    @Override
    public TrainingProgram updateTrainingProgram(TrainingProgram trainingProgram) {
        return trainingProgramDao.findById(trainingProgram.getId()).map(trainingProgram1 -> trainingProgramDao.update(trainingProgram1)).orElseThrow(()->new ResourceNotFoundException("TrainingProgram with id " + trainingProgram.getId() + ", is not found"));

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
        return trainingProgram.orElseThrow(()->new ResourceNotFoundException("TrainingProgram with id " + id + ", is not found"));
    }

    @Override
    public List<Intake> getIntakeByProgramId( int programId ) {
        Optional<TrainingProgram> trainingProgram = trainingProgramDao.findById( programId );
       return trainingProgram.map(trainingProgram1 -> trainingProgram1.getIntakes()).orElseThrow(()->new ResourceNotFoundException("TrainingProgram with id " + programId + ", is not found"));
    }
}

