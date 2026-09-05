package com.orientaplus.orientabackend.auth;

public class RegisterResponse {

    private long id;
    private String email;
    private String role;
    private String fullName;

    public RegisterResponse(long id,String email,String role,String fullName){
        this.id=id; this.email=email; this.role=role; this.fullName=fullName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }
}
