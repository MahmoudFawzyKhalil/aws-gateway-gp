package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.dao.TrainingProgramDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.exception.ResourceAlreadyExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
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
        try {
            return branchDao.save(branch);
        }
        catch (Exception e) {
            throw new ResourceAlreadyExistException("Track" + branch.getName() + ", is already exist!");
        }
    }


    @Override
    public Branch updateBranch( Branch branch ) {
        try{
        return branchDao.update( branch );
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Could not update branch with id ");
        }

    }

    @Override
    public List<Branch> getAllBranches() {
        return branchDao.findAll();
    }


    @Override
    public Branch getBranchById(Integer id ) {
        return branchDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Branch with id " + id + ", is not found"));
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
