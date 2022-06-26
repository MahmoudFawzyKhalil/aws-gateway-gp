package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.entity.Track;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        if (entity == null || entity.getId() == null) {
            throw new NullPointerException("track entity or id can't be null ");
        }
        return trackRepo.save(entity);
    }

    @Override
    public Optional<Track> findById(Integer integer) {
        return trackRepo.findById(integer);
    }

    @Override
    public <C> Optional<C> findById(Integer integer, Class<C> projection) {
        return trackRepo.findById(integer, projection);
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
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        Page<C> page = trackRepo.findBy(PageRequest.of(pageNumber, pageSize), projection);
        return page.getContent();
    }

    @Override
    public List<Track> findAllByExample(Track example) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return trackRepo.findAll(Example.of(example, caseInsensitiveExampleMatcher));
    }

    @Override
    public <C> List<C> findAllByExample(C example, Class<C> projection) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        return trackRepo.findAllBy(Example.of(example, caseInsensitiveExampleMatcher), projection);

    }

    @Override
    public <C> List<C> geAllByTrackId(int intakeId, Class<C> projection) {
        return trackRepo.findAllByIntake_Id(intakeId, projection);
    }

    @Override
    @Transactional
    public int removeUserFromTrack(int userId, int trackId) {
        return trackRepo.removeUserFromTrack(userId, trackId);
    }
}
