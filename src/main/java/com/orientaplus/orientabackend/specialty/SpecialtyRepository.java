package com.orientaplus.orientabackend.specialty;

import com.orientaplus.orientabackend.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {
    Specialty findByName (String name);
    Specialty findByUser (User user);

    List<Specialty> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}