package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.entity.Intake;
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

    @Override
    public Intake createIntake(Intake intake) {
        return intakeDao.save(intake);
    }

    @Override
    public Intake updateIntake(Intake intake) {
        return intakeDao.update(intake);
    }

    @Override
    public List<Intake> getAllIntakes() {
        return intakeDao.findAll();
    }

    @Override
    public Optional<Intake> getIntakeById(int id) {
        return intakeDao.findById(id);
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


}
