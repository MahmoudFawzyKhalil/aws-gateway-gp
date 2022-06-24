package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface TrackManagement {

    Track createTrack(Track track );

    Track updateTrack( Track track );

    List<Track> updateTracks (List<Track> tracks);

    Track getTrackById(Integer id );

    List<Track> getAllTracks();


    List<User> getUsersByTrackId(int trackId);

    //Optional<Track> getTrackById(int id);

}
