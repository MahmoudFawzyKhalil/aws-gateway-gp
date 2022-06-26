package eg.gov.iti.jets.api.resource.privilege;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.service.management.PrivilegeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_PRIVILEGES.name())")
    public ResponseEntity<?> getAllPrivileges(){
        GetAllPrivilegesResponse getPrivilegesResponse = new GetAllPrivilegesResponse();
        List<GetPrivilegeResponse> privileges = privilegeManagement.getAllPrivilege().stream().map(
                mapper::privilegeToGetPrivilegeResponse
        ) .collect(Collectors.toList());
        getPrivilegesResponse.setPrivileges(privileges);
        return new ResponseEntity<>(getPrivilegesResponse, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_PRIVILEGES.name())")
    public ResponseEntity<?> getPrivilege(@PathVariable("id") int id) {
        Privilege privilege = privilegeManagement.getPrivilegeById(id);
        return new ResponseEntity<>(mapper.privilegeToGetPrivilegeResponse(privilege), HttpStatus.OK);
    }

}
