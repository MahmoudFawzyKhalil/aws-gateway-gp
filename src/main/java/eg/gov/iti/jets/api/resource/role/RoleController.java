package eg.gov.iti.jets.api.resource.role;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.service.management.RoleManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleManagement roleManagement;
    private final Mapper mapper;

    @PostMapping
    public Boolean addRole(@RequestBody RoleRequest roleRequest) {
        return roleManagement.addRole(mapper.mapRoleRequestToRole(roleRequest));
    }

    @GetMapping
    public List<RoleResponse> getAllRoles() {
       return roleManagement.getAllRole().stream().map(mapper::mapRoleToRoleResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleResponse getRole(@PathVariable("id") int id){
       return mapper.mapRoleToRoleResponse(roleManagement.getRoleById(id));
    }
}
