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
    public ResponseEntity<RoleResponse> addRole(@RequestBody RoleRequest roleRequest) {
        Role role = roleManagement.addRole(mapper.roleRequestToRole(roleRequest));
        return new ResponseEntity<>(mapper.roleToRoleResponse(role), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetAllRolesResponse> getAllRoles() {
        GetAllRolesResponse getRolesResponse = new GetAllRolesResponse();
        getRolesResponse.setRoles(
                roleManagement.getAllRole().stream()
                        .map(mapper::roleToGetRoleResponse)
                        .collect(Collectors.toList())
        );
       return new ResponseEntity<>(getRolesResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRoleResponse> getRole(@PathVariable("id") Integer id){
       return new ResponseEntity<>(mapper.roleToGetRoleResponse(roleManagement.getRoleById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@RequestBody RoleRequest roleRequest, @PathVariable("id") Integer id) {
        Role role = mapper.roleRequestToRole(roleRequest);
        role.setId(id);
        role = roleManagement.updateRole(role);
        return new ResponseEntity<>(mapper.roleToRoleResponse(role), HttpStatus.OK);
    }

}
