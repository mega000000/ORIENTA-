package com.orientaplus.orientabackend.gamification;

import com.orientaplus.orientabackend.auth.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserBadge {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge;

    private LocalDateTime earnedAt;

    public UserBadge(){}

    public UserBadge(User user, Badge badge){
        this.user = user;
        this.badge = badge;
        this.earnedAt = LocalDateTime.now();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Badge getBadge() { return badge; }
    public void setBadge(Badge badge) { this.badge = badge; }

    public LocalDateTime getEarnedAt() { return earnedAt; }
    public void setEarnedAt(LocalDateTime earnedAt) { this.earnedAt = earnedAt; }
}