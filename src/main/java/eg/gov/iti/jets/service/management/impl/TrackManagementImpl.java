package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.service.management.CrudOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackManagementImpl implements CrudOperations<Track> {
    @Override
    public Boolean create(Track type) {
        return null;
    }

    @Override
    public Track update(Track type) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public List<Track> getAll() {
        return null;
    }

    @Override
    public Track getById(int id) {
        return null;
    }

    //TODO getTrackByName
}
