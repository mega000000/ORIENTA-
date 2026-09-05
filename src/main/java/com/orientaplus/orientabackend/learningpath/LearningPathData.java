package com.orientaplus.orientabackend.learningpath;

import com.orientaplus.orientabackend.specialty.Specialty;
import com.orientaplus.orientabackend.specialty.SpecialtyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class LearningPathData implements CommandLineRunner {

    private final SpecialtyRepository specialtyRepository;
    private final LearningPathRepository learningPathRepository;
    private final LearningStepRepository learningStepRepository;
    private final ResourceRepository resourceRepository;

    public LearningPathData(SpecialtyRepository specialtyRepository,
                            LearningPathRepository learningPathRepository,
                            LearningStepRepository learningStepRepository,
                            ResourceRepository resourceRepository){
        this.specialtyRepository = specialtyRepository;
        this.learningPathRepository = learningPathRepository;
        this.learningStepRepository = learningStepRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (learningPathRepository.count() > 0) {
            return;
        }

        Specialty cybersecurity = specialtyRepository.findByName("Analyste Cybersécurité");
        if (cybersecurity == null) return;

        LearningPath path = new LearningPath(cybersecurity, "Cybersecurity Analyst Path", "Beginner", 10);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Networking Fundamentals", "Understand TCP/IP, DNS, HTTP and network models.", 10),
                new LearningStep(path, 2, "Linux Systems", "Use the terminal, permissions, processes and logs.", 12),
                new LearningStep(path, 3, "Security Principles", "CIA triad, authentication, access control and risks.", 8),
                new LearningStep(path, 4, "Web Security Basics", "Understand common vulnerabilities and defenses.", 12),
                new LearningStep(path, 5, "Logs & Detection", "Read events and spot simple anomalies.", 10),
                new LearningStep(path, 6, "Final Project", "Build a simple incident tracking dashboard.", 15)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "VIDEO", "Networking Basics Explained", "YouTube", "https://youtube.com/example1"),
                new Resource(steps.get(1), "ARTICLE", "Linux Command Line Basics", "freeCodeCamp", "https://freecodecamp.org/example2"),
                new Resource(steps.get(2), "COURSE", "Introduction to Cybersecurity", "Coursera", "https://coursera.org/example3"),
                new Resource(steps.get(3), "ARTICLE", "OWASP Top 10", "OWASP", "https://owasp.org/example4"),
                new Resource(steps.get(4), "VIDEO", "Log Analysis for Beginners", "YouTube", "https://youtube.com/example5"),
                new Resource(steps.get(5), "PROJECT", "Build a SOC Dashboard", "Internal", "https://orientaplus.com/example6")
        ));
    }
}