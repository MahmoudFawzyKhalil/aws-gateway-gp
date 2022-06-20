package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.CrudOperations;
import eg.gov.iti.jets.service.management.IntakeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntakeManagementImpl implements IntakeManagement {

    @Autowired
    IntakeDao intakeDao;
    @Autowired
    TrainingProgramDao trainingProgramDao;
    @Autowired
    TrackDao trackDao;

    @Override
    public Intake createIntake(Intake intake) {
        return intakeDao.save(intake);
    }

    @Override
    public Intake updateIntake(Intake intake)
    {
        return intakeDao.update(intakeDao.findById(intake.getId()).orElseThrow(()->new ResourceNotFoundException("Intake with id " + intake.getId() + ", is not found")));


    }

    @Override
    public List<Intake> getAllIntakes() {
        return intakeDao.findAll();
    }

    @Override
    public Intake getIntakeById(int id) {
        return intakeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Intake with id " + id + ", is not found"));
    }


    @Override
    public List<Track> getTrackByIntakeId( int intakeId ) {
        Optional<Intake> intake = intakeDao.findById( intakeId );
        return  intake.map(intake1 -> intake1.getTracks()).orElseThrow(()->new ResourceNotFoundException("There is no tracks for that intake with id " + intakeId));

    }


//    @Override
//    public Optional<Intake> getUserInIntakeById(int id) {
//        Optional<Intake> intake = intakeDao.findById(id);
//        User User = userDao.
//    }



//    @Override
//    public Boolean delete(int id) {
//        return null;
//    }

//public void deleteIntake(int id){
//        intakeDao.remove(id);
//}
}
