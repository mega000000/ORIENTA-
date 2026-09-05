package com.orientaplus.orientabackend.gamification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Badge findByRuleCode(String ruleCode);
}