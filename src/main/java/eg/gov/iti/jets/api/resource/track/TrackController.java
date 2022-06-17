package eg.gov.iti.jets.api.resource.track;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.service.management.impl.TrackManagementImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/tracks" )

public class TrackController {
    final TrackManagementImpl trackManagement;
    final Mapper mapper;

    public TrackController( TrackManagementImpl trackManagement, Mapper mapper ) {
        this.trackManagement = trackManagement;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity<TrackResponseList> getTracks(){
        List<Track> tracks = trackManagement.getAllTracks();
        List<TrackResponse> trackResponses =  mapper.mapFromListOfTracksToListOfTrackResponses( tracks );
        TrackResponseList trackResponseList = new TrackResponseList();
        for ( TrackResponse response : trackResponses ) {
            trackResponseList.getTrackResponsesList().add( response );
        }
        return new ResponseEntity<>( trackResponseList , HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackResponse> getTrackById(@PathVariable int id){
        Optional<Track> track = trackManagement.getTrackById(id);
        TrackResponse trackResponse = new TrackResponse();
        if(track.isPresent()){
            trackResponse = mapper.mapFromTrackToTrackResponse( track.get() );
        }
        return new ResponseEntity<>( trackResponse ,HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<TrackResponse> createTrack(@RequestBody TrackRequest trackRequest){
        Track track = trackManagement.createTrack( mapper.mapFromTrackRequestToTrack( trackRequest ) );

        TrackResponse trackResponse = mapper.mapFromTrackToTrackResponse( track );
        return new ResponseEntity<>(trackResponse, HttpStatus.CREATED ) ;
    }



    @PutMapping("/{id}")
    public ResponseEntity<TrackResponse> updateTrack (@PathVariable int id , @RequestBody TrackPutRequest trackPutRequsert){
        Track track = trackManagement.updateTrack( mapper.mapFromTrackPutRequestToBranch(trackPutRequsert , id) );
        TrackResponse trackResponse = mapper.mapFromTrackToTrackResponse( track );
        return new ResponseEntity<>( trackResponse, HttpStatus.OK );
    }



}
