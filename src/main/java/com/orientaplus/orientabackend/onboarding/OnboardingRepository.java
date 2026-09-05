package com.orientaplus.orientabackend.onboarding;

import com.orientaplus.orientabackend.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding,Long> {
    Onboarding findByUser(User user);
}
