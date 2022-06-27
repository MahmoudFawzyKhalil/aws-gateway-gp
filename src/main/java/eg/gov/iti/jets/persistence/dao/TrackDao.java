package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.Track;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackDao extends GenericCrudDao<Track,Integer> {
    <C> List<C> geAllByTrackId(int intakeId, Class<C> projection);

    int removeUserFromTrack(int userId,int trackId);
}
