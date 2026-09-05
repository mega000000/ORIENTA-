package com.orientaplus.orientabackend.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    public User registerUser(String email,String password,String role,String fullName){

        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null){
            throw new ResourceNotFoundException("Email already in use");
        }
        String hashedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPasswordHash(hashedPassword);
        newUser.setRole(role);
        newUser.setFullName(fullName);
        return userRepository.save(newUser);
    }
    public User loginUser(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new ResourceNotFoundException("Invalid email or password");
        }
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }
        return user;
    }
}
