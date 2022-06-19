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
            throw new ResourceNotFoundException("Could not update track with id ");
        }
    }


    @Override
    public List<Track> getAllTracks() {
        return trackDao.findAll();
    }


    @Override
    public Track getTrackById(Integer id ) {
        return trackDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Track with id " + id + ", is not found"));
    }


}
