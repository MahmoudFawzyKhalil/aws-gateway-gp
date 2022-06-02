package eg.gov.iti.jets.api.resource.instructor;

import eg.gov.iti.jets.api.resource.trainingManager.TrainingManagerRequest;
import eg.gov.iti.jets.api.resource.trainingManager.TrainingManagerResponse;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.InstructorManagement;
import eg.gov.iti.jets.service.management.TrainingManagerManagement;
import eg.gov.iti.jets.service.model.Instructor;
import eg.gov.iti.jets.service.model.TrainingManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    final InstructorManagement instructorManagement;
    final Mapper mapper;

    public InstructorController( InstructorManagement instructorManagement , Mapper mapper){
        this.instructorManagement = instructorManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public Boolean createInstructor( @RequestBody InstructorRequest instructorRequest){
        return instructorManagement.createInstructor( mapper.mapFromInstructorRequestToInstructor( instructorRequest )  );
    }

    @PutMapping
    public InstructorResponse updateInstructor ( @RequestBody InstructorRequest instructorRequest){
        Instructor instructor = instructorManagement.updateInstructor( mapper.mapFromInstructorRequestToInstructor( instructorRequest ) );
        return mapper.mapFromInstructorToInstructorResponse( instructor );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteInstructor( @PathVariable int id){
        return instructorManagement.deleteInstructor( id );
    }

    @GetMapping("/{id}")
    public InstructorResponse getInstructorById(@PathVariable int id){
        return mapper.mapFromInstructorToInstructorResponse( instructorManagement.getInstructorById( id ) );
    }

    @GetMapping
    public List<InstructorResponse> getInstructors(){
        return instructorManagement.getAllInstructors()
                .stream().map( mapper::mapFromInstructorToInstructorResponse )
                .collect( Collectors.toList() );
    }
}

