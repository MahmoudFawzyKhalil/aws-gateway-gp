package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.management.CrudOperations;
import eg.gov.iti.jets.service.management.TrackManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackManagementImpl implements TrackManagement {

    @Autowired
    TrackDao trackDao;
    @Autowired
    IntakeDao intakeDao;

    @Override
    public Track createTrack(Track track) {

        return trackDao.save(track);
    }

    @Override
    public Track updateTrack(Track track) {
        return trackDao.update(track);
    }

    @Override
    public List<Track> getAllTracks() {
        return trackDao.findAll();
    }

    @Override
    public Optional<Track> getTrackById(int id) {
        return trackDao.findById(id);
    }


}
