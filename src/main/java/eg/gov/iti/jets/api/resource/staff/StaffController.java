package eg.gov.iti.jets.api.resource.staff;

import eg.gov.iti.jets.api.resource.student.StudentResponse;
import eg.gov.iti.jets.api.resource.student.StudentResponseList;
import eg.gov.iti.jets.service.model.UserAdapter;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.StaffManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffManagement staffManagement;
    private final Mapper mapper;
    public StaffController(StaffManagement staffManagement,Mapper mapper) {
        this.staffManagement = staffManagement;
        this.mapper=mapper;
    }

    @GetMapping
    public ResponseEntity<StaffResponseList> getAllStaff(){
        List<User> allStaff = staffManagement.getAllStaff();
        List<StaffResponse> staffResponses =  mapper.mapFromListOfStaffToListOfStaffResponse(allStaff);

        StaffResponseList staffResponseList = new StaffResponseList();
        for ( StaffResponse staffResponse : staffResponses ) {
            staffResponseList.getStaffResponse().add(staffResponse);
        }
        return new ResponseEntity( staffResponseList , HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity CreateStaff(@AuthenticationPrincipal UserAdapter userAdapter ,
                                      @RequestBody StaffRequestList staffRequestList) {
        int currentLoggedUserId = userAdapter.getId();
        staffManagement.createStaff(mapper.mapFromStaffRequestListToStaffList(currentLoggedUserId, staffRequestList));
        return new ResponseEntity("Staff Inserted Successfully", HttpStatus.CREATED);
    }

    //    @PutMapping
//    public ResponseEntity updateStaff(@RequestBody StaffUpdateRequest staffUpdateRequest){
//            staffManagement.updateStaff(mapper.mapFromStaffUpdateRequestToUser(staffUpdateRequest));
//        return ResponseEntity.ok().build();
//    }
}
