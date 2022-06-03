package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.api.util.Crud;
import eg.gov.iti.jets.service.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackManagement implements Crud<Track> {
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
