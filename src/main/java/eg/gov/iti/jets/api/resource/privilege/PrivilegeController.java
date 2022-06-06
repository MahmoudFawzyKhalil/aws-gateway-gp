package eg.gov.iti.jets.api.resource.privilege;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.service.management.PrivilegeManagement;
import lombok.RequiredArgsConstructor;
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
    public GetPrivilegesResponse getAllPrivileges(){
        GetPrivilegesResponse getPrivilegesResponse = new GetPrivilegesResponse();
        List<PrivilegeTypeResponse> privileges = privilegeManagement.getAllPrivilege().stream().map(
                mapper::mapPrivilegeToPrivilegeTypeResponse
        ) .collect(Collectors.toList());
        getPrivilegesResponse.setPrivileges(privileges);
        return getPrivilegesResponse;
    }

    @PostMapping
    public Boolean addPrivilege(@RequestBody PrivilegeRequest privilegeRequest) {
      return privilegeManagement.addPrivilege(mapper.mapPrivilegeRequestToPrivilege(privilegeRequest));
    }

    @GetMapping("/{id}")
    public PrivilegeTypeResponse getPrivilege(@PathVariable("id") int id) {
        Privilege privilege = privilegeManagement.getPrivilegeById(id);
        return mapper.mapPrivilegeToPrivilegeTypeResponse(privilege);
    }

}
