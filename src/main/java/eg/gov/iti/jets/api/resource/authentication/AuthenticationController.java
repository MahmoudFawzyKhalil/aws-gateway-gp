package eg.gov.iti.jets.api.resource.authentication;

import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController( UserManagement userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authReq) {
        String jwt = authenticate(authReq.getUsername(), authReq.getPassword());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.ACCEPTED);
    }

//    @GetMapping("/admin")
////    @Secured("WRITE")          //security worked only after using this annotation not using authorities in security configurer
//    public String testWrite() {
//        return "test write";
//    }
//    @GetMapping("/hello")
//    public String hello() {
//        return "test hello";
//    }

    private String authenticate(String username, String password){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException e){
            throw new ResourceNotFoundException("Incorrect username or password", e);
        }
        UserAdapter userDetails = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }
}
