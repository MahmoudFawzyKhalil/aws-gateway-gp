package eg.gov.iti.jets.api.resource.staff;

import com.sun.xml.bind.v2.TODO;
import eg.gov.iti.jets.persistence.dao.TrackDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.service.management.TrackManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.StaffManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffManagement staffManagement;
    private final TrackManagement trackManagement;
    private final Mapper mapper;
    private final UserDao userDao;
    private final TrackDao trackDao;


    public StaffController( StaffManagement staffManagement, TrackManagement trackManagement, Mapper mapper, UserDao userDao, TrackDao trackDao ) {
        this.staffManagement = staffManagement;
        this.trackManagement = trackManagement;
        this.mapper = mapper;
        this.userDao = userDao;
        this.trackDao = trackDao;
    }

    // TODO: 6/25/2022 priivilegge view staff
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
        System.out.println("controller ============= " + id);
        System.out.println(staffUpdateRequest.getRolename());
        staffManagement.updateStaff(mapper.mapFromStaffUpdateRequestToUser(staffUpdateRequest, id));
        return new ResponseEntity<>("Staff updated Successfully", HttpStatus.OK);
    }
    @GetMapping("/instructors")
    //just supervisor can access it
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_INSTRUCTOR.name())")
    public ResponseEntity<?> getInstructorsUnderSupervisor(@AuthenticationPrincipal UserAdapter userAdapter){

        List<User> allInstructors = staffManagement.getAllInstructors(mapper.mapFromUserAdapterToUser(userAdapter));
        List<StaffResponse> staffResponses = mapper.mapFromListOfStaffToListOfStaffResponse(allInstructors);
        return new ResponseEntity<>(staffResponses, HttpStatus.OK);
    }

}
