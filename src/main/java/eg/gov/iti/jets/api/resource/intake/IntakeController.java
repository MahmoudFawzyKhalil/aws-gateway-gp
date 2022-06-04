package eg.gov.iti.jets.api.resource.intake;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.service.management.impl.IntakeManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/intakes")
public class IntakeController {
    final IntakeManagementImpl intakeManagement;
    final Mapper mapper;

    public IntakeController( IntakeManagementImpl intakeManagement , Mapper mapper){
        this.intakeManagement=intakeManagement ;
        this.mapper=mapper;
    }

    @PostMapping
    public Boolean createIntake( @RequestBody IntakeRequest intakeRequest){
        return intakeManagement.create( mapper.mapFromIntakeRequestToIntake( intakeRequest )  );
    }

    @PutMapping
    public IntakeResponse updateIntake (@RequestBody IntakeRequest intakeRequest){
        Intake intake = intakeManagement.update( mapper.mapFromIntakeRequestToIntake( intakeRequest ) );
        return mapper.mapFromIntakeToIntakeResponse( intake );
    }

    @DeleteMapping("/{id}")
    public Boolean deleteIntake( @PathVariable int id){
        return intakeManagement.delete( id );
    }

    @GetMapping("/{id}")
    public IntakeResponse getIntakeById(@PathVariable int id){
        return mapper.mapFromIntakeToIntakeResponse( intakeManagement.getById( id ) );
    }

    @GetMapping
    public List<IntakeResponse> getIntakes(){
        return intakeManagement.getAll()
                .stream().map( mapper::mapFromIntakeToIntakeResponse )
                .collect( Collectors.toList() );
    }



}
