package com.orientaplus.orientabackend.gamification;

import java.util.List;

public class GamificationResponse {
    private int xp;
    private List<String> badges;

    public GamificationResponse(int xp, List<String> badges){
        this.xp = xp;
        this.badges = badges;
    }

    public int getXp() { return xp; }
    public List<String> getBadges() { return badges; }
}