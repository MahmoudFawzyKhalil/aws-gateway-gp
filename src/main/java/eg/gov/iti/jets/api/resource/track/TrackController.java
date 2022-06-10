package eg.gov.iti.jets.api.resource.track;

import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.intake.IntakeResponseList;
import eg.gov.iti.jets.api.resource.intake.IntakeViewResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.service.management.impl.TrackManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public TrackResponseList getTracks() {
        List<Track> tracks = trackManagement.getAllTracks();
        List<TrackResponse> trackResponses = mapper.mapFromListOfTracksToListOfTrackResponses( tracks );
        TrackResponseList trackResponseList = new TrackResponseList();
        for ( TrackResponse response : trackResponses ) {
            trackResponseList.getTrackResponsesList().add( response );
        }
        return trackResponseList;
    }


    @GetMapping( "/{id}" )
    public TrackViewResponse getTrackById( @PathVariable int id ) {
        Optional<Track> track = trackManagement.getTrackById( id );
        return track.map( value -> new TrackViewResponse( true, mapper.mapFromTrackToTrackResponse( value ) ) ).orElseGet( () -> new TrackViewResponse( false, null ) );
    }


    @PostMapping
    public TrackResponse createTrack( @RequestBody TrackRequest trackRequest ) {
        Track track = trackManagement.createTrack( mapper.mapFromTrackRequestToTrack( trackRequest ) );
        return mapper.mapFromTrackToTrackResponse( track );
    }


    @PutMapping
    public TrackResponse updateTrack( @RequestBody TrackRequest trackRequest ) {
        Track track = trackManagement.updateTrack( mapper.mapFromTrackRequestToTrack( trackRequest ) );
        return mapper.mapFromTrackToTrackResponse( track );
    }

    //    @DeleteMapping("/{id}")
//    public Boolean deleteTrack( @PathVariable int id){
//        return trackManagement.delete( id );
//    }
    @GetMapping( "{intakeId}/track" )
    public TrackResponseList getTrackByIntakeId( @PathVariable int intakeId ) {
        List<Track> listOfTrack = trackManagement.getTrackByIntakeId( intakeId );
        List<TrackResponse> listOfResponse = new ArrayList<>();
        listOfTrack.forEach( track -> listOfResponse.add( mapper.mapFromTrackToTrackResponse( track ) ) );
        return new TrackResponseList( listOfResponse );
    }


}
