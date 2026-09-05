package com.orientaplus.orientabackend.assessment;

public class SessionResponse {
    private long sessionId;
    private String status;

    public SessionResponse(long sessionId, String status){
        this.sessionId = sessionId;
        this.status = status;
    }

    public long getSessionId() { return sessionId; }
    public String getStatus() { return status; }
}