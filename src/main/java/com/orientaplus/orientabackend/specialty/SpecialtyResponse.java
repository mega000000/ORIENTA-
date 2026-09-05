package com.orientaplus.orientabackend.specialty;

public class SpecialtyResponse {

    private long id;
    private String name;
    private String description;
    private String missions;
    private String tools;
    private String prerequisites;
    private String outlook;

    public SpecialtyResponse(long id, String name, String description, String missions, String tools, String prerequisites, String outlook) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.missions = missions;
        this.tools = tools;
        this.prerequisites = prerequisites;
        this.outlook = outlook;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getMissions() { return missions; }
    public String getTools() { return tools; }
    public String getPrerequisites() { return prerequisites; }
    public String getOutlook() { return outlook; }
}