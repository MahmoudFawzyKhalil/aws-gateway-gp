//package eg.gov.iti.jets.api.resource.supervisor;
//
//import eg.gov.iti.jets.api.util.Mapper;
//import eg.gov.iti.jets.service.management.SupervisorManagement;
//import eg.gov.iti.jets.service.model.Supervisor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/supervisors")
//public class SupervisorController {
//    final SupervisorManagement supervisorManagement;
//    final Mapper mapper;
//
//    public SupervisorController(SupervisorManagement supervisorManagement , Mapper mapper){
//        this.supervisorManagement = supervisorManagement;
//        this.mapper=mapper;
//    }
//
//    @PostMapping
//    public Boolean createSupervisor( @RequestBody SupervisorRequest supervisorRequest){
//        return supervisorManagement.createIntake( mapper.mapFromSupervisorRequestToSupervisor( supervisorRequest )  );
//    }
//
//    @PutMapping
//    public SupervisorResponse updateSupervisor (@RequestBody SupervisorRequest supervisorRequest){
//        Supervisor supervisor = supervisorManagement.updateSupervisor( mapper.mapFromSupervisorRequestToSupervisor( supervisorRequest ) );
//        return mapper.mapFromSupervisorToSupervisorResponse( supervisor );
//    }
//
//    @DeleteMapping("/{id}")
//    public Boolean deleteSupervisor( @PathVariable int id){
//        return supervisorManagement.deleteSupervisor( id );
//    }
//
//    @GetMapping("/{id}")
//    public SupervisorResponse getSupervisorById(@PathVariable int id){
//        return mapper.mapFromSupervisorToSupervisorResponse( supervisorManagement.getSupervisorById(id) );
//    }
//
//    @GetMapping
//    public List<SupervisorResponse> getSupervisors(){
//        return supervisorManagement.getAllSupervisors()
//                .stream().map( mapper::mapFromSupervisorToSupervisorResponse )
//                .collect( Collectors.toList() );
//    }
//
//}
