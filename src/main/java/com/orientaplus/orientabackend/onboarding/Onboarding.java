package com.orientaplus.orientabackend.onboarding;

import com.orientaplus.orientabackend.auth.User;
import jakarta.persistence.*;


@Entity
public class Onboarding {
    @Id
    @GeneratedValue
    private long id;
    private String studyLevel;
    private Integer weeklyAvailableHours;
    private String objective;
    @OneToOne
    @JoinColumn(name ="user_id",unique = true)
    private User user;

    public Onboarding() {
    }
    public Onboarding (
            String studyLevel,
            Integer weeklyAvailableHours,
            String objective,
            User user
    ){
        this.studyLevel=studyLevel;this.weeklyAvailableHours=weeklyAvailableHours;
        this.objective=objective;this.user=user;
    }
    //setters

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public void setWeeklyAvailableHours(Integer weeklyAvailableHours) {
        this.weeklyAvailableHours = weeklyAvailableHours;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setUser(User user) {
        this.user = user;
    }
//

    //getters

    public String getStudyLevel() {
        return studyLevel;
    }

    public Integer getWeeklyAvailableHours() {
        return weeklyAvailableHours;
    }

    public String getObjective() {
        return objective;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
//
}
