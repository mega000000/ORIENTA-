package com.orientaplus.orientabackend.auth;

import com.orientaplus.orientabackend.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    public AuthController(UserService userService,JwtService jwtService){
        this.userService=userService;
        this.jwtService=jwtService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        User user = userService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getRole(),
                request.getFullName()
        );
        RegisterResponse registerResponse=new RegisterResponse(user.getId(),user.getEmail(),user.getRole(),user.getFullName());
        return ok(registerResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){

        User user = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

        LoginResponse loginResponse =new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                jwtService.generateToken(user),
                user.getFullName()
        );

            return ok(loginResponse);
    }

}
