package eg.gov.iti.jets.api.resource.authentication;

import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class AuthenticationController {
    private final UserManagement userService;

    public AuthenticationController( UserManagement userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authReq) {
        String jwt = userService.authenticate(authReq.getUsername(), authReq.getPassword()); //todo userService injection to authenticate
//        String jwt = authenticate(authReq.getUsername(), authReq.getPassword());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin")
//    @Secured("WRITE")          //security worked only after using this annotation not using authorities in security configurer
    public String testWrite() {
        return "test write";
    }
    @GetMapping("/hello")
    public String hello() {
        return "test hello";
    }

}
