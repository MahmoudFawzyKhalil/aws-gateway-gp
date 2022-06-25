package eg.gov.iti.jets.api.resource.track;

import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.role.GetRoleResponse;
import eg.gov.iti.jets.api.resource.role.RoleResponse;
//import eg.gov.iti.jets.api.resource.role.UpdateRoleRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.GetTrainingProgramsResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.api.resource.user.UserResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.TrackManagementImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tracks")

public class TrackController {
    final TrackManagementImpl trackManagement;
    final Mapper mapper;

    public TrackController(TrackManagementImpl trackManagement, Mapper mapper) {
        this.trackManagement = trackManagement;
        this.mapper = mapper;
    }


    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_INSTRUCTORS.name())")
    public ResponseEntity<TrackResponseList> getTracks() {
        List<Track> tracks = trackManagement.getAllTracks();
        List<TrackResponse> trackResponses = mapper.mapFromListOfTracksToListOfTrackResponses(tracks);
        TrackResponseList trackResponseList = new TrackResponseList();
        for (TrackResponse response : trackResponses) {
            trackResponseList.getTrackResponsesList().add(response);
        }
        return new ResponseEntity<>(trackResponseList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_INSTRUCTORS.name())")
    public ResponseEntity<TrackResponse> getTrackById(@PathVariable("id") int id) {
        return new ResponseEntity<>(mapper.mapFromTrackToTrackResponse(trackManagement.getTrackById(id)), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TRACKS.name())")
    public ResponseEntity<TrackResponse> createTrack(@Valid @RequestBody TrackRequest trackRequest) {
        Track track = trackManagement.createTrack(mapper.mapFromTrackRequestToTrack(trackRequest));

        TrackResponse trackResponse = mapper.mapFromTrackToTrackResponse(track);
        return new ResponseEntity<>(trackResponse, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TRACKS.name())")
    public ResponseEntity<TrackResponse> updateTrack(@PathVariable int id, @Valid @RequestBody TrackPutRequest trackPutRequsert) {
        Track track = trackManagement.updateTrack(mapper.mapFromTrackPutRequestToTrack(trackPutRequsert, id));
        TrackResponse trackResponse = mapper.mapFromTrackToTrackResponse(track);
        return new ResponseEntity<>(trackResponse, HttpStatus.OK);
    }

    @GetMapping("{trackId}/users")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_INSTRUCTORS.name())")
    public ResponseEntity<?> getUsersByTrackId(@PathVariable int trackId) {
        List<User> usersByTrackId = trackManagement.getUsersByTrackId(trackId);
        List<UserResponse> trackMembers = usersByTrackId.stream().map(mapper::mapFromUserToUserResponse).collect(Collectors.toList());
        return new ResponseEntity<>(trackMembers, HttpStatus.OK);
    }
    @GetMapping("{trackId}/students")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_INSTRUCTORS.name())")
    public ResponseEntity<?> getStudentsByTrackId(@PathVariable int trackId) {
        List<User> studentsByTrackId = trackManagement.getStudentsByTrackId(trackId);
        List<UserResponse> studentsByTrack = studentsByTrackId.stream().map(mapper::mapFromUserToUserResponse).collect(Collectors.toList());
        return new ResponseEntity<>(studentsByTrack, HttpStatus.OK);
    }

}
