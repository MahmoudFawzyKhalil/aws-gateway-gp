package eg.gov.iti.jets.api.resource.staff;


import eg.gov.iti.jets.service.model.UserAdapter;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.StaffManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffManagement staffManagement;
    private final Mapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_STAFF.name())")
    public ResponseEntity<?> getAllStaff() {
        List<User> allStaff = staffManagement.getAllStaff();
        List<StaffResponse> staffResponses = mapper.mapFromListOfStaffToListOfStaffResponse(allStaff);

        StaffResponseList staffResponseList = new StaffResponseList();
        for (StaffResponse staffResponse : staffResponses) {
            staffResponseList.getStaffResponse().add(staffResponse);
        }
        return new ResponseEntity<>(staffResponseList, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_STAFF.name())")
    public ResponseEntity<?> CreateStaff(@AuthenticationPrincipal UserAdapter userAdapter,
                                      @RequestBody StaffRequestList staffRequestList) {
        int currentLoggedUserId = userAdapter.getId();
        staffManagement.createStaff(mapper.mapFromStaffRequestListToStaffList(currentLoggedUserId, staffRequestList));
        return new ResponseEntity<>("Staff Inserted Successfully", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_STAFF.name())")
    public ResponseEntity<?> updateStaff(@RequestBody StaffUpdateRequest staffUpdateRequest, @PathVariable int id) {
        staffManagement.updateStaff(mapper.mapFromStaffUpdateRequestToUser(staffUpdateRequest, id));
        return new ResponseEntity<>("Staff updated Successfully", HttpStatus.OK);
    }
    @GetMapping("/instructors")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_INSTRUCTOR.name())")
    public ResponseEntity<?> getInstructorsUnderSupervisor(@AuthenticationPrincipal UserAdapter userAdapter){

        List<User> allInstructors = staffManagement.getAllInstructors(mapper.mapFromUserAdapterToUser(userAdapter));
        List<StaffResponse> staffResponses = mapper.mapFromListOfStaffToListOfStaffResponse(allInstructors);
        return new ResponseEntity<>(staffResponses, HttpStatus.OK);
    }

}
