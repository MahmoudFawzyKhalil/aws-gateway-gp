package eg.gov.iti.jets.api.resource.privilege;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.service.management.PrivilegeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/privileges")
@RequiredArgsConstructor
public class PrivilegeController {
    private final PrivilegeManagement privilegeManagement;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllPrivileges(){
        GetAllPrivilegesResponse getPrivilegesResponse = new GetAllPrivilegesResponse();
        List<GetPrivilegeResponse> privileges = privilegeManagement.getAllPrivilege().stream().map(
                mapper::privilegeToGetPrivilegeResponse
        ) .collect(Collectors.toList());
        getPrivilegesResponse.setPrivileges(privileges);
        return new ResponseEntity<>(getPrivilegesResponse, HttpStatus.OK);
    }

//    @PostMapping
//    public Boolean addPrivilege(@RequestBody AddPrivilegeRequest addPrivilegeRequest) {
//      return privilegeManagement.addPrivilege(mapper.addPrivilegeRequestToPrivilege(addPrivilegeRequest));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrivilege(@PathVariable("id") int id) {
        Privilege privilege = privilegeManagement.getPrivilegeById(id);
        return new ResponseEntity<>(mapper.privilegeToGetPrivilegeResponse(privilege), HttpStatus.OK);
    }

}
