package com.orientaplus.orientabackend.learningpath;

public class ResourceResponse {
    private long id;
    private String type;
    private String title;
    private String provider;
    private String url;

    public ResourceResponse(long id, String type, String title, String provider, String url){
        this.id = id;
        this.type = type;
        this.title = title;
        this.provider = provider;
        this.url = url;
    }

    public long getId() { return id; }
    public String getType() { return type; }
    public String getTitle() { return title; }
    public String getProvider() { return provider; }
    public String getUrl() { return url; }
}