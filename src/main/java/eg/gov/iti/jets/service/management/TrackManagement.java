package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;

import java.util.List;
import java.util.Optional;

public interface TrackManagement {

    Track createTrack(Track track );

    Track updateTrack( Track track );

    Track getTrackById(Integer id );

    List<Track> getAllTracks();

    //Optional<Track> getTrackById(int id);

}
