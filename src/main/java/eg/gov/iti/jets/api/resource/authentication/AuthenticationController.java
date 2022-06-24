package eg.gov.iti.jets.api.resource.authentication;

import eg.gov.iti.jets.api.config.BlackListingService;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.util.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class AuthenticationController {
    private final UserManagement userService;

    @Autowired
    private BlackListingService blackListingService;

    public AuthenticationController( UserManagement userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authReq) {
        String jwt = userService.authenticate(authReq.getUsername(), authReq.getPassword()); //todo userService injection to authenticate
//        String jwt = authenticate(authReq.getUsername(), authReq.getPassword());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.ACCEPTED);
    }


    @PutMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserAdapter userAdapter) {
        System.out.println("the jwt to be blacklisted"+userAdapter.getJwt());
        blackListingService.blackListJwt(userAdapter.getJwt());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/testlogout")
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).START_STOP_VIEW_INSTANCE.name())")
    public int testLogout(){
        return 545;
    }


}
