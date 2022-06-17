package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.management.BranchManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchManagementImpl implements BranchManagement {

    @Autowired
    BranchDao branchDao;
    @Autowired
    TrainingProgramDao trainingProgramDao;

    @Override
    public Branch createBranch( Branch branch ) {
        return branchDao.save(branch);
    }

    @Override
    public Branch updateBranch( Branch branch ) {
        Branch update = branchDao.update( branch );
        System.out.println("marwaaa  "+update);
        return update;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchDao.findAll();
    }

    @Override
    public Optional<Branch> getBranchById(int id ) {
        return branchDao.findById(id);
    }

    @Override
    public List<TrainingProgram> getTrainingProgramByBranchId( int branchId ) {
        Optional<Branch> branch = branchDao.findById( branchId );
        TrainingProgram trainingProgram = new TrainingProgram();
        branch.ifPresent( trainingProgram::setBranch );
        List<TrainingProgram> listOfTrainingProgram = trainingProgramDao.findAllByExample( trainingProgram );
        return listOfTrainingProgram;
    }

}
