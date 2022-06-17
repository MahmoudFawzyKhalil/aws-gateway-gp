package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.Track;

import java.util.List;

public interface TrackDao extends GenericCrudDao<Track,Integer> {
    <C> List<C> geAllByTrackId(int intakeId, Class<C> projection);
}
