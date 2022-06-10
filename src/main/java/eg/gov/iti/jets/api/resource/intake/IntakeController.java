package eg.gov.iti.jets.api.resource.intake;


import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.service.management.impl.IntakeManagementImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/intakes")
public class IntakeController {
    final IntakeManagementImpl intakeManagement;
    final Mapper mapper;

    public IntakeController( IntakeManagementImpl intakeManagement , Mapper mapper){
        this.intakeManagement=intakeManagement ;
        this.mapper=mapper;
    }

    @GetMapping
    public IntakeResponseList getIntakes(){
        List<Intake> intakes = intakeManagement.getAllIntakes();
        List<IntakeResponse> intakeResponses =  mapper.mapFromListOfIntakesToListOfIntakeResponses(intakes);
        IntakeResponseList intakeResponseList = new IntakeResponseList();
        for(IntakeResponse response : intakeResponses){
            intakeResponseList.getIntakeResponsesList().add(response);
        }
        return intakeResponseList;
    }


    @GetMapping("/{id}")
    public IntakeViewResponse getIntakeById(@PathVariable int id){
        Optional<Intake> intake = intakeManagement.getIntakeById(id);
        return intake.map( value -> new IntakeViewResponse( true, mapper.mapFromIntakeToIntakeResponse(value))).orElseGet( () -> new IntakeViewResponse( false, null ) );
    }


    @PostMapping
    public IntakeResponse createIntake( @RequestBody IntakeRequest intakeRequest){
        Intake intake = intakeManagement.createIntake( mapper.mapFromIntakeRequestToIntake( intakeRequest )  );
        return mapper.mapFromIntakeToIntakeResponse(intake);
    }


    @PutMapping
    public IntakeResponse updateIntake (@RequestBody IntakeRequest intakeRequest){
        Intake intake = intakeManagement.updateIntake( mapper.mapFromIntakeRequestToIntake( intakeRequest ) );
        return mapper.mapFromIntakeToIntakeResponse( intake );
    }


//    @DeleteMapping("/{id}")
//    public Boolean deleteIntake( @PathVariable int id){
//        return intakeManagement.delete( id );
//    }

    @GetMapping("{programId}/intake")
    public IntakeResponseList getIntakeByProgramId (@PathVariable int programId){
        List<Intake> listOfIntake = intakeManagement.getIntakeByProgramId(programId);
        List<IntakeResponse> listOfResponse = new ArrayList<>();
        listOfIntake.forEach( intake -> listOfResponse.add( mapper.mapFromIntakeToIntakeResponse( intake ) ) );
        return new IntakeResponseList(listOfResponse);
    }






}
