package eg.gov.iti.jets.api.resource.track;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.service.management.impl.TrackManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tracks")

public class TrackController {
    final TrackManagementImpl trackManagement;
    final Mapper mapper;

    public TrackController( TrackManagementImpl trackManagement , Mapper mapper){
        this.trackManagement = trackManagement;
        this.mapper = mapper;
    }


    @PostMapping
    public Boolean createTrack( @RequestBody TrackRequest trackRequest){
        return trackManagement.create( mapper.mapFromTrackRequestToTrack( trackRequest )  );
    }

    @PutMapping
    public TrackResponse updateTrack (@RequestBody TrackRequest trackRequest){
        Track track = trackManagement.update( mapper.mapFromTrackRequestToTrack( trackRequest ) );
        return mapper.mapFromTrackToTrackResponse( track );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTrack( @PathVariable int id){
        return trackManagement.delete( id );
    }

    @GetMapping("/{id}")
    public TrackResponse getTrackById(@PathVariable int id){
        return mapper.mapFromTrackToTrackResponse( trackManagement.getById( id ) );
    }

    @GetMapping
    public List<TrackResponse> getTrainingPrograms(){
        return trackManagement.getAll()
                .stream().map( mapper::mapFromTrackToTrackResponse )
                .collect( Collectors.toList() );
    }


}
