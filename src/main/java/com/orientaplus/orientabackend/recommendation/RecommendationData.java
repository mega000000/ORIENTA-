package com.orientaplus.orientabackend.recommendation;

import com.orientaplus.orientabackend.specialty.Specialty;
import com.orientaplus.orientabackend.specialty.SpecialtyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class RecommendationData implements CommandLineRunner {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyInterestProfileRepository profileRepository;

    public RecommendationData(SpecialtyRepository specialtyRepository, SpecialtyInterestProfileRepository profileRepository){
        this.specialtyRepository = specialtyRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (profileRepository.count() > 0) {
            return;
        }

        // R, I, A, S, E, C profiles per specialty (order matches Day 1 seeding order)
        double[][] profiles = {
                {60, 40, 30, 30, 40, 60}, // Développeur Frontend -> A/I leaning actually let's fix below
        };

        List<Specialty> specialties = specialtyRepository.findAll();

        // Manually mapped RIASEC profiles by specialty name
        for (Specialty specialty : specialties) {
            double r=0, i=0, a=0, s=0, e=0, c=0;

            switch (specialty.getName()) {
                case "Développeur Frontend" -> { r=20; i=50; a=70; s=20; e=20; c=30; }
                case "Développeur Backend Java" -> { r=30; i=80; a=20; s=10; e=20; c=60; }
                case "Développeur Full Stack" -> { r=30; i=70; a=50; s=20; e=30; c=50; }
                case "Développeur Mobile" -> { r=30; i=60; a=60; s=20; e=30; c=40; }
                case "Data Analyst" -> { r=10; i=85; a=20; s=20; e=20; c=70; }
                case "Data Engineer" -> { r=30; i=80; a=15; s=10; e=20; c=65; }
                case "Analyste Cybersécurité" -> { r=50; i=80; a=10; s=20; e=20; c=60; }
                case "Ingénieur Cloud/DevOps" -> { r=60; i=70; a=15; s=20; e=30; c=55; }
                case "QA/Test Engineer" -> { r=30; i=60; a=15; s=30; e=15; c=80; }
                case "UX/UI Designer" -> { r=15; i=40; a=90; s=50; e=30; c=25; }
                default -> { r=40; i=40; a=40; s=40; e=40; c=40; }
            }

            SpecialtyInterestProfile profile = new SpecialtyInterestProfile(specialty, r, i, a, s, e, c);
            profileRepository.save(profile);
        }
    }
}