package com.orientaplus.orientabackend.profile;

public class ProfileResponse {

    private long id;
    private String email;
    private String role;
    private String fullName;

    public ProfileResponse(long id, String email, String role, String fullName){
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
