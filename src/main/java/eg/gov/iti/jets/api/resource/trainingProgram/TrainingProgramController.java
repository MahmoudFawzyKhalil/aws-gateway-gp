package eg.gov.iti.jets.api.resource.trainingProgram;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.management.TrainingProgramManagement;
import eg.gov.iti.jets.service.management.impl.TrainingProgramManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainingprogram")
public class TrainingProgramController {
    final TrainingProgramManagement trainingProgramManagement;
    final Mapper mapper;

    public TrainingProgramController( TrainingProgramManagementImpl trainingProgramManagement , Mapper mapper){
        this.trainingProgramManagement = trainingProgramManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public Boolean createTrainingProgram( @RequestBody TrainingProgramRequest trainingProgramRequest){

        TrainingProgram trainingProgram = mapper.mapFromTrainingProgramRequestToTrainingProgram( trainingProgramRequest );
        return trainingProgramManagement.createTrainingProgram(trainingProgram );
    }

    @PutMapping
    public TrainingProgramResponse updateTrainingProgram ( @RequestBody TrainingProgramPutRequest trainingProgramPutRequest){
        TrainingProgram trainingProgram = trainingProgramManagement.updateTrainingProgram( mapper.mapFromTrainingProgramPutRequestToTrainingProgram( trainingProgramPutRequest ) );
        return mapper.mapFromTrainingProgramToTrainingProgramResponse( trainingProgram );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTrainingProgram( @PathVariable int id){
        return trainingProgramManagement.deleteTrainingProgram( id );
    }

    @GetMapping("/{id}")
    public TrainingProgramResponse getTrainingProgramById(@PathVariable int id){
        TrainingProgram trainingProgram = trainingProgramManagement.getTrainingProgramById( id );
        return mapper.mapFromTrainingProgramToTrainingProgramResponse(trainingProgram);
    }

    @GetMapping
    public GetTrainingProgramsResponse getTrainingPrograms(){
        GetTrainingProgramsResponse getTrainingProgramsResponse = new GetTrainingProgramsResponse();
        getTrainingProgramsResponse.setTrainingPrograms(  trainingProgramManagement.getAllTrainingPrograms()
                .stream().map( mapper::mapFromTrainingProgramToTrainingProgramResponse )
                .collect( Collectors.toList() ));
        System.out.println(getTrainingProgramsResponse);
        return getTrainingProgramsResponse;

    }

    @GetMapping("{branchId}/trainingprogram")
    GetTrainingProgramsResponse getTrainingProgramsByBranchId(@PathVariable int branchId){
        return null;
    }

}
