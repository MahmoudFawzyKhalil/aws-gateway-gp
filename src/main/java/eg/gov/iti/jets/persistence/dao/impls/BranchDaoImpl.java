package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BranchDaoImpl implements BranchDao {
    private final BranchRepo branchRepo;

    public BranchDaoImpl(BranchRepo branchRepo) {
        this.branchRepo = branchRepo;
    }


    @Override
    public Branch save(Branch entity) {
        return branchRepo.save(entity);
    }

    @Override
    public Branch update(Branch entity) {
        return null;
    }

    @Override
    public Optional<Branch> findById(Integer integer) {
        return Optional.empty();
    }
}