package com.orientaplus.orientabackend.gamification;

import jakarta.persistence.*;

@Entity
public class Badge {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String ruleCode;
    private int xpReward;

    public Badge(){}

    public Badge(String name, String ruleCode, int xpReward){
        this.name = name;
        this.ruleCode = ruleCode;
        this.xpReward = xpReward;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public int getXpReward() { return xpReward; }
    public void setXpReward(int xpReward) { this.xpReward = xpReward; }
}