package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.service.model.Branch;

import java.util.List;

public interface BranchManagement {
    Boolean createBranch( Branch branch );
    Branch updateBranch( Branch branch );
    Boolean deleteBranch( int id );
    List<Branch> getAllBranches();
    Branch getBranchById( int id );
}
