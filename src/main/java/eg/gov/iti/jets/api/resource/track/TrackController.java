package eg.gov.iti.jets.api.resource.track;


import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.resource.user.UserResponseList;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.TrackManagement;
import eg.gov.iti.jets.service.management.impl.TrackManagementImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")

public class TrackController {
    final TrackManagement trackManagement;
    final Mapper mapper;

    public TrackController(TrackManagement trackManagement, Mapper mapper) {
        this.trackManagement = trackManagement;
        this.mapper = mapper;
    }


    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_TRACKS.name())")
    public ResponseEntity<?> getTracks() {
        List<Track> tracks = trackManagement.getAllTracks();
        List<TrackResponse> trackResponses = mapper.mapFromListOfTracksToListOfTrackResponses(tracks);
        TrackResponseList trackResponseList = new TrackResponseList();
        for (TrackResponse response : trackResponses) {
            trackResponseList.getTrackResponsesList().add(response);
        }
        return new ResponseEntity<>(trackResponseList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_TRACKS.name())")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id) {
        return new ResponseEntity<>(mapper.mapFromTrackToTrackResponse(trackManagement.getTrackById(id)), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TRACKS.name())")
    public ResponseEntity<?> createTrack(@Valid @RequestBody TrackRequest trackRequest) {
        Track track = trackManagement.createTrack(mapper.mapFromTrackRequestToTrack(trackRequest));

        TrackResponse trackResponse = mapper.mapFromTrackToTrackResponse(track);
        return new ResponseEntity<>(trackResponse, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TRACKS.name())")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @Valid @RequestBody TrackPutRequest trackPutRequest) {
        Track track = trackManagement.updateTrack(mapper.mapFromTrackPutRequestToTrack(trackPutRequest, id));
        TrackResponse trackResponse = mapper.mapFromTrackToTrackResponse(track);
        return new ResponseEntity<>(trackResponse, HttpStatus.OK);
    }

    @GetMapping("{trackId}/users")
    public ResponseEntity<?> getUsersByTrackId(@PathVariable int trackId) {
        List<User> usersByTrackId = trackManagement.getUsersByTrackId(trackId);
        List<UserResponse> userResponses = mapper.mapFromListOfUsersToListOfUserResponses( usersByTrackId );

        return new ResponseEntity<>(new UserResponseList(userResponses), HttpStatus.OK);
    }
    @GetMapping("{trackId}/students")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_STUDENTS.name())")
    public ResponseEntity<?> getStudentsByTrackId(@PathVariable int trackId) {
        List<User> studentsByTrackId = trackManagement.getStudentsByTrackId(trackId);
        List<UserResponse> userResponses = mapper.mapFromListOfUsersToListOfUserResponses( studentsByTrackId );
        return new ResponseEntity<>(new UserResponseList( userResponses ), HttpStatus.OK);
    }

}
