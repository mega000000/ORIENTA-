package com.orientaplus.orientabackend.onboarding;

import com.orientaplus.orientabackend.auth.User;
import jakarta.persistence.*;

public class OnboardingResponse {

        private long id;
        private String studyLevel;
        private Integer weeklyAvailableHours;
        private String objective;


        public OnboardingResponse (
                long id,
                String studyLevel,
                Integer weeklyAvailableHours,
                String objective
        ){
            this.studyLevel=studyLevel;this.weeklyAvailableHours=weeklyAvailableHours;
            this.objective=objective;this.id=id;
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


        }
