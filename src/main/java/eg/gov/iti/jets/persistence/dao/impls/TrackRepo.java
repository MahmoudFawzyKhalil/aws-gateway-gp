package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Track;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface TrackRepo extends JpaRepository<Track, Integer> {
    <C> Optional<C> findById(Integer id, Class<C> projection);

    <C> Page<C> findBy(PageRequest pageRequest, Class<C> projection);

    <C> List<C> findAllBy(Example<C> example, Class<C> projection);

    <C> List<C> findAllByIntake_Id(int intakeId, Class<C> projection);

    @Modifying
    @Query(value = "delete  from user_tracks where user_id=:userId and track_id=:trackId",nativeQuery = true)
    int removeUserFromTrack(int userId,int trackId);

}
