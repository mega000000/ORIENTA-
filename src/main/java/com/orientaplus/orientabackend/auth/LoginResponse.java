package com.orientaplus.orientabackend.auth;

public class LoginResponse {

    private long id;
    private String email;
    private String role;
    private String loginToken;
    private String fullName;

    public LoginResponse(long id, String email, String role,String loginToken,String fullName){

        this.id=id; this.email=email; this.role=role; this.loginToken = loginToken; this.fullName=fullName;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public String getFullName() {
        return fullName;
    }
}
