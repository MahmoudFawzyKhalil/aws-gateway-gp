package eg.gov.iti.jets.api.resource.track;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.TrackManagement;
import eg.gov.iti.jets.service.model.Track;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tracks")

public class TrackController {
    final TrackManagement trackManagement;
    final Mapper mapper;

    public TrackController( TrackManagement trackManagement , Mapper mapper){
        this.trackManagement = trackManagement;
        this.mapper = mapper;
    }


    @PostMapping
    public Boolean createTrack( @RequestBody TrackRequest trackRequest){
        return trackManagement.createTrack( mapper.mapFromTrackRequestToTrack( trackRequest )  );
    }

    @PutMapping
    public TrackResponse updateTrack (@RequestBody TrackRequest trackRequest){
        Track track = trackManagement.updateTrack( mapper.mapFromTrackRequestToTrack( trackRequest ) );
        return mapper.mapFromTrackToTrackResponse( track );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTrack( @PathVariable int id){
        return trackManagement.deleteTrack( id );
    }

    @GetMapping("/{id}")
    public TrackResponse getTrackById(@PathVariable int id){
        return mapper.mapFromTrackToTrackResponse( trackManagement.getTrackById( id ) );
    }

    @GetMapping
    public List<TrackResponse> getTrainingPrograms(){
        return trackManagement.getAllTracks()
                .stream().map( mapper::mapFromTrackToTrackResponse )
                .collect( Collectors.toList() );
    }


}
