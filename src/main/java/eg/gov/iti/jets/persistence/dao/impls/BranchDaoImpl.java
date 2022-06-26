package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        if (entity == null || entity.getId() == null) {
            throw new NullPointerException("entity or id can't be null");
        }
        return branchRepo.save(entity);
    }

    @Override
    public Optional<Branch> findById(Integer id) {
        return branchRepo.findById(id);
    }

    @Override
    public <C> Optional<C> findById(Integer id, Class<C> projection) {
        return branchRepo.findById(id, projection);
    }

    @Override
    public List<Branch> findAll() {
        return branchRepo.findAll();
    }

    @Override
    public List<Branch> findAll(int pageNumber, int pageSize) {
        Page<Branch> branchPage = branchRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return branchPage.getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        Page<C> page = branchRepo.findBy(PageRequest.of(pageNumber, pageSize), projection);
        return page.getContent();
    }

    @Override
    public List<Branch> findAllByExample(Branch example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return branchRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return branchRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher), projection);
    }


    @Override
    public Long countAll() {
        return branchRepo.countAllBy();
    }
}
