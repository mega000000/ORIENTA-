package com.orientaplus.orientabackend.onboarding;

public class OnboardingRequest {

    private String studyLevel;
    private Integer weeklyAvailableHours;
    private String objective;

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Integer getWeeklyAvailableHours() {
        return weeklyAvailableHours;
    }

    public void setWeeklyAvailableHours(Integer weeklyAvailableHours) {
        this.weeklyAvailableHours = weeklyAvailableHours;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }
}
