package com.orientaplus.orientabackend.specialty;

import com.orientaplus.orientabackend.auth.User;
import jakarta.persistence.*;

@Entity
public class Specialty {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String missions;

    @Column(length = 1000)
    private String tools;

    @Column(length = 1000)
    private String prerequisites;

    @Column(length = 1000)
    private String outlook;

    @OneToOne
    @JoinColumn(name="user_id", unique = true)
    private User user;

    public Specialty(){}

    public Specialty(String name, String description){
        this.name = name; this.description = description;
    }

    public Specialty(String name, String description, String missions, String tools, String prerequisites, String outlook){
        this.name = name;
        this.description = description;
        this.missions = missions;
        this.tools = tools;
        this.prerequisites = prerequisites;
        this.outlook = outlook;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMissions() { return missions; }
    public void setMissions(String missions) { this.missions = missions; }

    public String getTools() { return tools; }
    public void setTools(String tools) { this.tools = tools; }

    public String getPrerequisites() { return prerequisites; }
    public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }

    public String getOutlook() { return outlook; }
    public void setOutlook(String outlook) { this.outlook = outlook; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}