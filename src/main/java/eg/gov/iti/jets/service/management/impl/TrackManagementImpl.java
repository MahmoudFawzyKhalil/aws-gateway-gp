package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.IntakeDao;
import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.TrackManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackManagementImpl implements TrackManagement {

    @Autowired
    TrackDao trackDao;

    @Override
    public Track createTrack(Track track) {
    try{
        return trackDao.save(track);
    }
    catch (Exception e) {
        throw new ResourceExistException("Track" + track.getName() + ", is already exist!");
    }
}

    @Override
    public Track updateTrack(Track track) {
        try {
            return trackDao.update(track);
        }
    catch (Exception e) {
        throw new ResourceNotFoundException("Could not update track with id " + track.getId());
        }
    }

//    @Override
//    public Track updateTrack(int id ) {
//        try {
//            Optional<Track> track = trackDao.findById(id);
//
//        }
//        catch (Exception e) {
//            throw new ResourceNotFoundException("Could not update role with id " + track.getId());
//        }
//    }


    @Override
    public List<Track> getAllTracks() {
        return trackDao.findAll();
    }

    @Override
    public Optional<Track> getTrackById(int id) {
        try {
            return trackDao.findById(id);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Could not update Track with id "+ id +" because its not found");
        }
    }


}
