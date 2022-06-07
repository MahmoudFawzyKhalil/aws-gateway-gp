package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.service.management.BranchManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchManagementImpl implements BranchManagement {

    @Autowired
    BranchDao branchDao;

    @Override
    public Branch createBranch( Branch branch ) {
        return branchDao.save(branch);
    }

    @Override
    public Branch updateBranch( Branch branch ) {
        return branchDao.save(branch);
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchDao.findAll();
    }

    @Override
    public Optional<Branch> getBranchById(int id ) {
        return branchDao.findById(id);
    }

}
