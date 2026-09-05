package com.orientaplus.orientabackend.auth;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    private String email;
    private String role;
    private String password;
    @NotBlank
    private String fullName;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}
