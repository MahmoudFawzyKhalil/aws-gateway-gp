package eg.gov.iti.jets.api.resource.role;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.service.management.RoleManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleManagement roleManagement;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<RoleResponse> addRole(@RequestBody AddRoleRequest roleRequest) {
        Role role = roleManagement.addRole(mapper.addRoleRequestToRole(roleRequest));
        return new ResponseEntity<>(mapper.roleToRoleResponse(role), HttpStatus.CREATED);
    }

    @GetMapping
    public GetAllRolesResponse getAllRoles() {
        GetAllRolesResponse getRolesResponse = new GetAllRolesResponse();
        getRolesResponse.setRoles(
                roleManagement.getAllRole().stream()
                        .map(mapper::roleToRoleResponse)
                        .collect(Collectors.toList())
        );
       return getRolesResponse;
    }

    @GetMapping("/{id}")
    public RoleResponse getRole(@PathVariable("id") int id){
       return mapper.roleToRoleResponse(roleManagement.getRoleById(id));
    }

    @PutMapping
    public Boolean updateRole(@RequestBody UpdateRoleRequest updateRoleRequest) {
        Role role = mapper.updateRoleRequestToRole(updateRoleRequest);
        return roleManagement.updateRole(role);
    }

}
