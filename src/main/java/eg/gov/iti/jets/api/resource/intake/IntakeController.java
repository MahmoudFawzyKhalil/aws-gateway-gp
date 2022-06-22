package eg.gov.iti.jets.api.resource.intake;


import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.track.TrackResponseList;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.service.management.IntakeManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/intakes")
public class IntakeController {
    final IntakeManagement intakeManagement;
    final Mapper mapper;


    public IntakeController( IntakeManagement intakeManagement , Mapper mapper){
        this.intakeManagement=intakeManagement ;
        this.mapper=mapper;
    }

    @GetMapping
    public ResponseEntity<?> getIntakes(){
        List<Intake> intakes = intakeManagement.getAllIntakes();
        List<IntakeResponse> intakeResponses =  mapper.mapFromListOfIntakesToListOfIntakeResponses(intakes);
        IntakeResponseList intakeResponseList = new IntakeResponseList();
        for(IntakeResponse response : intakeResponses){
            intakeResponseList.getIntakeResponsesList().add(response);
        }
        return new ResponseEntity<>( intakeResponseList , HttpStatus.OK );
    }


    @GetMapping("/{id}")
    public ResponseEntity<IntakeResponse> getIntakeById(@PathVariable int id){
        Intake intake = intakeManagement.getIntakeById(id);
        return new ResponseEntity<>(mapper.mapFromIntakeToIntakeResponse(intake),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<IntakeResponse> createIntake( @RequestBody IntakeRequest intakeRequest){
        Intake intake = intakeManagement.createIntake( mapper.mapFromIntakeRequestToIntake( intakeRequest )  );
        return new ResponseEntity<>(mapper.mapFromIntakeToIntakeResponse(intake),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<IntakeResponse> updateIntake (@PathVariable int id , @RequestBody IntakePutRequest intakeRequest){
        Intake intake = intakeManagement.updateIntake( mapper.mapFromIntakePutRequestToIntake( id, intakeRequest ) );
        return new ResponseEntity<>(mapper.mapFromIntakeToIntakeResponse( intake ),HttpStatus.OK);
    }



    @GetMapping("{intakeId}/tracks")
    public ResponseEntity<TrackResponseList> getTrackByIntakeId( @PathVariable int intakeId){
        List<Track> tracks = intakeManagement.getTrackByIntakeId( intakeId );
        List<TrackResponse> tracksResponse = new ArrayList<>();
        tracks.forEach( track -> tracksResponse.add( mapper.mapFromTrackToTrackResponse( track ) ) );
        TrackResponseList trackResponseList = new TrackResponseList( tracksResponse );
        return new ResponseEntity<>(  trackResponseList  , HttpStatus.OK);
    }

    // TODO: 6/20/2022 add in Dao 
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteIntake(@PathVariable int id){
//        Intake intake = intakeManagement.deleteIntake(id);
//        return new ResponseEntity<>(true,HttpStatus.OK);
//
//    }

}
