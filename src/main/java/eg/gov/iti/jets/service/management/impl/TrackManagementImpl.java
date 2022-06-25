package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.TrackManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackManagementImpl implements TrackManagement {

    @Autowired
    TrackDao trackDao;

    @Autowired
    UserDao userDao;


    @Override
    public Track createTrack(Track track) {
        try {
            return trackDao.save(track);
        } catch (Exception e) {
            throw new ResourceExistException("Track" + track.getName() + ", is already exist!");
        }
    }


    @Override
    public Track updateTrack(Track track) {
        try {
            return trackDao.update(track);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Could not update track with id ");
        }
    }

    @Override
    public List<Track> updateTracks(List<Track> tracks) {
        List<Track> trackList = new ArrayList<>();
        for(Track track : tracks){
            System.out.println("track management :: "+ track.getName());
            try {
                 trackDao.update(track);
                 trackList.add(track);
            } catch (Exception e) {
                throw new ResourceNotFoundException("Could not assign  track with id [ " + track.getId()
                                                    + " ] to the user because it's already assigned");
            }
        }
        return trackList;
    }


    @Override
    public List<Track> getAllTracks() {
        return trackDao.findAll();
    }


    @Override
    public Track getTrackById(Integer id) {
        return trackDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Track with id " + id + ", is not found"));
    }

    @Override
    public List<User> getUsersByTrackId(int trackId) {
        Optional<Track> track = trackDao.findById(trackId);
        return userDao.findAllUsersByTrack(track.orElseThrow());
    }


}
