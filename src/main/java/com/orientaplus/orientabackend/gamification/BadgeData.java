package com.orientaplus.orientabackend.gamification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(4)
public class BadgeData implements CommandLineRunner {

    private final BadgeRepository badgeRepository;

    public BadgeData(BadgeRepository badgeRepository){
        this.badgeRepository = badgeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (badgeRepository.count() > 0) return;

        badgeRepository.saveAll(List.of(
                new Badge("Profile Discovered", "RIASEC_COMPLETED", 100),
                new Badge("Path Started", "PATH_STARTED", 50),
                new Badge("Path Completed", "PATH_COMPLETED", 500)
        ));
    }
}