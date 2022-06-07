package eg.gov.iti.jets.api.resource.role;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.service.management.RoleManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleManagement roleManagement;
    private final Mapper mapper;

    @PostMapping
    public Boolean addRole(@RequestBody AddRoleRequest roleRequest) {
        return roleManagement.addRole(mapper.addRoleRequestToRole(roleRequest));
    }

    @GetMapping
    public GetAllRolesResponse getAllRoles() {
        GetAllRolesResponse getRolesResponse = new GetAllRolesResponse();
        getRolesResponse.setRoles(
                roleManagement.getAllRole().stream()
                        .map(mapper::roleToGetRoleResponse)
                        .collect(Collectors.toList())
        );
       return getRolesResponse;
    }

    @GetMapping("/{id}")
    public GetRoleResponse getRole(@PathVariable("id") int id){
       return mapper.roleToGetRoleResponse(roleManagement.getRoleById(id));
    }

    @PutMapping
    public Boolean updateRole(@RequestBody UpdateRoleRequest updateRoleRequest) {
        Role role = mapper.updateRoleRequestToRole(updateRoleRequest);
        return roleManagement.updateRole(role);
    }

}
