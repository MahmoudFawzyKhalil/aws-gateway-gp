//package eg.gov.iti.jets.api.resource.intake;
//
//
//import eg.gov.iti.jets.api.util.Mapper;
//import eg.gov.iti.jets.service.management.IntakeManagement;
//import eg.gov.iti.jets.service.model.Intake;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/intakes")
//public class IntakeController {
//    final IntakeManagement intakeManagement;
//    final Mapper mapper;
//
//    public IntakeController(IntakeManagement intakeManagement , Mapper mapper){
//        this.intakeManagement=intakeManagement ;
//        this.mapper=mapper;
//    }
//
//    @PostMapping
//    public Boolean createBranch( @RequestBody IntakeRequest intakeRequest){
//        return intakeManagement.createIntake( mapper.mapFromIntakeRequestToIntake( intakeRequest )  );
//    }
//
//    @PutMapping
//    public IntakeResponse updateBranch (@RequestBody IntakeRequest intakeRequest){
//        Intake intake = intakeManagement.updateIntake( mapper.mapFromIntakeRequestToIntake( intakeRequest ) );
//        return mapper.mapFromIntakeToIntakeResponse( intake );
//    }
//
//    @DeleteMapping("/{id}")
//    public Boolean deleteIntake( @PathVariable int id){
//        return intakeManagement.deleteIntake( id );
//    }
//
//    @GetMapping("/{id}")
//    public IntakeResponse getIntakeById(@PathVariable int id){
//        return mapper.mapFromIntakeToIntakeResponse( intakeManagement.getIntakeById( id ) );
//    }
//
//    @GetMapping
//    public List<IntakeResponse> getBranches(){
//        return intakeManagement.getAllIntakes()
//                .stream().map( mapper::mapFromIntakeToIntakeResponse )
//                .collect( Collectors.toList() );
//    }
//
//
//
//}
