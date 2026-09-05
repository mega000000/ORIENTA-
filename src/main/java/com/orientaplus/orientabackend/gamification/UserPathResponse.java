package com.orientaplus.orientabackend.gamification;

public class UserPathResponse {
    private long id;
    private String pathTitle;
    private String status;
    private double progress;

    public UserPathResponse(long id, String pathTitle, String status, double progress){
        this.id = id;
        this.pathTitle = pathTitle;
        this.status = status;
        this.progress = progress;
    }

    public long getId() { return id; }
    public String getPathTitle() { return pathTitle; }
    public String getStatus() { return status; }
    public double getProgress() { return progress; }
}