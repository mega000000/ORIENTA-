package com.orientaplus.orientabackend.recommendation;

import com.orientaplus.orientabackend.specialty.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyInterestProfileRepository extends JpaRepository<SpecialtyInterestProfile, Long> {
    SpecialtyInterestProfile findBySpecialty(Specialty specialty);
}