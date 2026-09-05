package com.orientaplus.orientabackend.profile;


import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.auth.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final UserRepository userRepository;


    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(Authentication authentication){

        long userId = Long.parseLong((String)authentication.getPrincipal());

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User Not Found");
        }

        User user = optionalUser.get();
        ProfileResponse profileResponse= new ProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getFullName()
        );

return ResponseEntity.ok(profileResponse);
    }
    @PutMapping("/profile")
  public ResponseEntity<?> profileUpdate(
            Authentication authentication,
            @Valid @RequestBody UpdateProfileRequest request){

        long userId=Long.parseLong((String)authentication.getPrincipal());

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("User Not Found");
        }
        User user = optionalUser.get();
        user.setFullName(request.getFullName());
        userRepository.save(user);
        ProfileResponse profileResponse = new ProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getFullName()
        );
        return ResponseEntity.ok(profileResponse);
  }

}
