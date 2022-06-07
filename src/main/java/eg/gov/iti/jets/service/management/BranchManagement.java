package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchManagement {
    Branch createBranch( Branch branch );
    Branch updateBranch( Branch branch );
    List<Branch> getAllBranches();
    Optional<Branch> getBranchById(int id );
}
