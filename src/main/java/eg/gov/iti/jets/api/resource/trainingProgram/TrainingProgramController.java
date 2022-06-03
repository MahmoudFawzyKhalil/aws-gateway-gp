package eg.gov.iti.jets.api.resource.trainingProgram;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.impl.TrainingProgramManagement;
import eg.gov.iti.jets.service.model.TrainingProgram;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainingprogram")
public class TrainingProgramController {
    final TrainingProgramManagement trainingProgramManagement;
    final Mapper mapper;

    public TrainingProgramController( TrainingProgramManagement trainingProgramManagement , Mapper mapper){
        this.trainingProgramManagement = trainingProgramManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public Boolean createTrainingProgram( @RequestBody TrainingProgramRequest trainingProgramRequest){
        return trainingProgramManagement.createTrainingProgram( mapper.mapFromTrainingProgramRequestToTrainingProgram( trainingProgramRequest )  );
    }

    @PutMapping
    public TrainingProgramResponse updateTrainingProgram ( @RequestBody TrainingProgramRequest trainingProgramRequest){
        TrainingProgram trainingProgram = trainingProgramManagement.updateTrainingProgram( mapper.mapFromTrainingProgramRequestToTrainingProgram( trainingProgramRequest ) );
        return mapper.mapFromTrainingProgramToTrainingProgramResponse( trainingProgram );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTrainingProgram( @PathVariable int id){
        return trainingProgramManagement.deleteTrainingProgram( id );
    }

    @GetMapping("/{id}")
    public TrainingProgramResponse getTrainingProgramById(@PathVariable int id){
        return mapper.mapFromTrainingProgramToTrainingProgramResponse( trainingProgramManagement.getTrainingProgramById( id ) );
    }

    @GetMapping
    public List<TrainingProgramResponse> getTrainingPrograms(){
        return trainingProgramManagement.getAllTrainingPrograms()
                .stream().map( mapper::mapFromTrainingProgramToTrainingProgramResponse )
                .collect( Collectors.toList() );
    }

}
