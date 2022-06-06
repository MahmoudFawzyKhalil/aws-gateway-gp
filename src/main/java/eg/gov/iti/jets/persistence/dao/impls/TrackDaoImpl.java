package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.entity.Track;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrackDaoImpl implements TrackDao {
    private final TrackRepo trackRepo;

    public TrackDaoImpl(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    @Override
    public Track save(Track entity) {
        return trackRepo.save(entity);
    }

    @Override
    public Track update(Track entity) {
        return trackRepo.save(entity);
    }

    @Override
    public Optional<Track> findById(Integer integer) {
        return trackRepo.findById(integer);
    }

    @Override
    public <C> Optional<Track> findById(Integer integer, Class<C> projection) {
        return Optional.empty();
    }

    @Override
    public List<Track> findAll() {
        return trackRepo.findAll();
    }

    @Override
    public List<Track> findAll(int pageNumber, int pageSize) {
        Page<Track> trackPage = trackRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return trackPage.getContent();
    }

    @Override
    public <C> List<Track> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return null;
    }

    @Override
    public List<Track> findAllByExample(Track example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return trackRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<Track> findAllByExample(Track example, Class<C> projection) {
        return null;
    }
}
