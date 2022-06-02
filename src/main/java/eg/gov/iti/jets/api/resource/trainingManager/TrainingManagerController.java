package eg.gov.iti.jets.api.resource.trainingManager;

import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.TrainingManagerManagement;
import eg.gov.iti.jets.service.management.TrainingProgramManagement;
import eg.gov.iti.jets.service.model.TrainingManager;
import eg.gov.iti.jets.service.model.TrainingProgram;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainingmanager")
public class TrainingManagerController {

    final TrainingManagerManagement trainingManagerManagement;
    final Mapper mapper;

    public TrainingManagerController( TrainingManagerManagement trainingManagerManagement , Mapper mapper){
        this.trainingManagerManagement = trainingManagerManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public Boolean createTrainingManager( @RequestBody TrainingManagerRequest trainingManagerRequest){
        return trainingManagerManagement.createTrainingManager( mapper.mapFromTrainingManagerRequestToTrainingManager( trainingManagerRequest )  );
    }

    @PutMapping
    public TrainingManagerResponse updateTrainingManager ( @RequestBody TrainingManagerRequest trainingManagerRequest){
        TrainingManager trainingManager = trainingManagerManagement.updateTrainingManager( mapper.mapFromTrainingManagerRequestToTrainingManager( trainingManagerRequest ) );
        return mapper.mapFromTrainingManagerToTrainingManagerResponse( trainingManager );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTrainingManager( @PathVariable int id){
        return trainingManagerManagement.deleteTrainingManager( id );
    }

    @GetMapping("/{id}")
    public TrainingManagerResponse getTrainingManagerById(@PathVariable int id){
        return mapper.mapFromTrainingManagerToTrainingManagerResponse( trainingManagerManagement.getTrainingManagerById( id ) );
    }

    @GetMapping
    public List<TrainingManagerResponse> getTrainingManagers(){
        return trainingManagerManagement.getAllTrainingManagers()
                .stream().map( mapper::mapFromTrainingManagerToTrainingManagerResponse )
                .collect( Collectors.toList() );
    }
}
