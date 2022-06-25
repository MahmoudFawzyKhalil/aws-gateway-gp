package eg.gov.iti.jets.api.resource.role;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.service.management.RoleManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleManagement roleManagement;
    private final Mapper mapper;

    @PostMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_ROLES.name())")
    public ResponseEntity<RoleResponse> addRole(@Valid @RequestBody RoleRequest roleRequest) {
        Role role = roleManagement.addRole(mapper.roleRequestToRole(roleRequest));
        return new ResponseEntity<>(mapper.roleToRoleResponse(role), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_ROLES.name())")
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
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_ROLES.name())")
    public ResponseEntity<GetRoleResponse> getRole(@PathVariable("id") Integer id){
       return new ResponseEntity<>(mapper.roleToGetRoleResponse(roleManagement.getRoleById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_ROLES.name())")
    public ResponseEntity<RoleResponse> updateRole(@Valid @RequestBody RoleRequest roleRequest, @PathVariable("id") Integer id) {
        Role role = mapper.roleRequestToRole(roleRequest);
        role.setId(id);
        role = roleManagement.updateRole(role);
        return new ResponseEntity<>(mapper.roleToRoleResponse(role), HttpStatus.OK);
    }
}
