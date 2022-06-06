package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.dao.KeyPairDao;
import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KeyPairDaoImpl implements KeyPairDao {

    private final KeyPairRepo keyPairRepo;

    public KeyPairDaoImpl(KeyPairRepo keyPairRepo) {
        this.keyPairRepo = keyPairRepo;
    }

    @Override
    public KeyPair save(KeyPair entity) {
        return keyPairRepo.save(entity);
    }

    @Override
    public KeyPair update(KeyPair entity) {
        return keyPairRepo.save(entity);
    }

    @Override
    public Optional<KeyPair> findById(Integer integer) {
        return keyPairRepo.findById(integer);
    }

    @Override
    public <C> Optional<C> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<KeyPair> findAll() {
        return keyPairRepo.findAll();
    }

    @Override
    public List<KeyPair> findAll(int pageNumber, int pageSize) {
        Page<KeyPair> keyPairPage=keyPairRepo.findAll(PageRequest.of(pageNumber,pageSize));
        return keyPairPage.getContent();
    }

    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<KeyPair> findAllByExample(KeyPair example) {
        ExampleMatcher  caseInsensitiveExampleMatcher=ExampleMatcher.matchingAll().withIgnoreCase();
        return keyPairRepo.findAll(Example.of(example,caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        return null;
    }

}
