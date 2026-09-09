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
        seedCybersecurity();
        seedFullStack();
        seedFrontend();
        seedBackendJava();
        seedMobile();
        seedDataAnalyst();
        seedDataEngineer();
        seedDevOps();
        seedQA();
        seedUIUX();
    }

    private void seedCybersecurity() {
        Specialty spec = specialtyRepository.findByName("Analyste Cybersécurité");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Cybersecurity Analyst Path", "Beginner", 10);
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
                new Resource(steps.get(0), "VIDEO", "Networking Basics Explained", "YouTube", "https://youtube.com"),
                new Resource(steps.get(1), "ARTICLE", "Linux Command Line Basics", "freeCodeCamp", "https://freecodecamp.org"),
                new Resource(steps.get(2), "COURSE", "Introduction to Cybersecurity", "Coursera", "https://coursera.org"),
                new Resource(steps.get(3), "ARTICLE", "OWASP Top 10", "OWASP", "https://owasp.org"),
                new Resource(steps.get(4), "VIDEO", "Log Analysis for Beginners", "YouTube", "https://youtube.com"),
                new Resource(steps.get(5), "PROJECT", "Build a SOC Dashboard", "Internal", "https://orientaplus.com")
        ));
    }

    private void seedFullStack() {
        Specialty spec = specialtyRepository.findByName("Développeur Full Stack");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Full Stack Developer Roadmap", "Intermediate", 12);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "HTML, CSS & Modern JavaScript", "Master ES6+, DOM manipulation and async programming.", 15),
                new LearningStep(path, 2, "React.js & Frontend Architecture", "Components, Hooks, State management and API integration.", 20),
                new LearningStep(path, 3, "Backend & REST APIs (Spring Boot)", "Build secure RESTful endpoints, auth, and business logic.", 25),
                new LearningStep(path, 4, "Databases & ORM", "Relational database design (PostgreSQL) and JPA/Hibernate.", 15),
                new LearningStep(path, 5, "Full Stack Integration & Deployment", "Connect frontend with backend, Docker basics, and deployment.", 20)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "COURSE", "Modern JavaScript Complete Guide", "freeCodeCamp", "https://freecodecamp.org"),
                new Resource(steps.get(1), "DOCUMENTATION", "React Official Documentation", "React", "https://react.dev"),
                new Resource(steps.get(2), "COURSE", "Spring Boot 3 Crash Course", "YouTube", "https://youtube.com"),
                new Resource(steps.get(3), "ARTICLE", "PostgreSQL Database Tutorial", "PostgreSQL", "https://postgresql.org"),
                new Resource(steps.get(4), "PROJECT", "Deploy Fullstack App to Cloud", "Internal", "https://github.com")
        ));
    }

    private void seedFrontend() {
        Specialty spec = specialtyRepository.findByName("Développeur Frontend");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Modern Frontend Engineer Path", "Beginner", 8);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Modern CSS & UI Libraries", "Flexbox, CSS Grid, Responsive Design and Material UI.", 12),
                new LearningStep(path, 2, "JavaScript Algorithms & State", "Data structures, closures, Promises and async/await.", 15),
                new LearningStep(path, 3, "React Mastery & Routing", "React 18+, React Router v6, custom hooks and context.", 20),
                new LearningStep(path, 4, "Performance & Testing", "Bundle optimization, responsive design best practices, and testing.", 15)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "DOCUMENTATION", "MUI Component Guidelines", "Material UI", "https://mui.com"),
                new Resource(steps.get(1), "COURSE", "JavaScript Deep Dive", "MDN Web Docs", "https://developer.mozilla.org"),
                new Resource(steps.get(2), "VIDEO", "React Full Course", "YouTube", "https://youtube.com"),
                new Resource(steps.get(3), "ARTICLE", "Web Performance Best Practices", "web.dev", "https://web.dev")
        ));
    }

    private void seedBackendJava() {
        Specialty spec = specialtyRepository.findByName("Développeur Backend Java");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Java & Spring Boot Backend Path", "Intermediate", 12);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Core Java & OOP", "Generics, Collections, Streams, and Lambdas.", 15),
                new LearningStep(path, 2, "Spring Boot & REST APIs", "Controllers, Services, DTO pattern, and Exception Handling.", 20),
                new LearningStep(path, 3, "Database Persistence with JPA", "Spring Data JPA, Hibernate relationships, and migrations.", 15),
                new LearningStep(path, 4, "Security & JWT Auth", "Spring Security 6, JWT token generation, and role authorization.", 15),
                new LearningStep(path, 5, "Microservices & Testing", "JUnit 5, Mockito, and containerization with Docker.", 20)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "COURSE", "Java Programming Masterclass", "freeCodeCamp", "https://freecodecamp.org"),
                new Resource(steps.get(1), "DOCUMENTATION", "Spring Boot Guides", "Spring.io", "https://spring.io/guides"),
                new Resource(steps.get(2), "ARTICLE", "Hibernate Performance Tuning", "Baeldung", "https://baeldung.com"),
                new Resource(steps.get(3), "VIDEO", "Spring Security & JWT Complete Tutorial", "YouTube", "https://youtube.com"),
                new Resource(steps.get(4), "PROJECT", "E-Commerce REST API Project", "GitHub", "https://github.com")
        ));
    }

    private void seedMobile() {
        Specialty spec = specialtyRepository.findByName("Développeur Mobile");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Mobile App Development Path", "Beginner", 10);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Mobile Fundamentals & Dart/Kotlin", "Understand mobile OS lifecycles and core language.", 15),
                new LearningStep(path, 2, "UI & Component Design", "Build responsive screens, navigation, and custom layouts.", 15),
                new LearningStep(path, 3, "State Management & REST Client", "Handle application state and connect to backend APIs.", 20),
                new LearningStep(path, 4, "Local Storage & Offline Mode", "Implement SQLite or SharedPreferences for offline data.", 12),
                new LearningStep(path, 5, "Mobile Capstone Project", "Publish or package a fully functional mobile application.", 18)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "COURSE", "Flutter & Dart Basics", "YouTube", "https://youtube.com"),
                new Resource(steps.get(1), "DOCUMENTATION", "Flutter Layout & UI Guide", "Flutter Docs", "https://flutter.dev"),
                new Resource(steps.get(2), "ARTICLE", "Connecting Mobile App to Spring Boot API", "Medium", "https://medium.com"),
                new Resource(steps.get(3), "COURSE", "Local Storage in Mobile", "Coursera", "https://coursera.org"),
                new Resource(steps.get(4), "PROJECT", "E-Commerce Mobile Application", "GitHub", "https://github.com")
        ));
    }

    private void seedDataAnalyst() {
        Specialty spec = specialtyRepository.findByName("Data Analyst");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Data Analyst Career Roadmap", "Beginner", 8);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Advanced Excel & Spreadsheets", "Formulas, Pivot Tables, and VLOOKUP/XLOOKUP.", 10),
                new LearningStep(path, 2, "SQL for Data Analysis", "Complex queries, window functions, aggregates, and joins.", 18),
                new LearningStep(path, 3, "Python for Data Analysis", "Pandas, NumPy, and cleaning messy real-world datasets.", 20),
                new LearningStep(path, 4, "BI & Data Visualization", "Building business dashboards with Power BI or Tableau.", 15)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "COURSE", "Excel for Data Analytics", "Coursera", "https://coursera.org"),
                new Resource(steps.get(1), "ARTICLE", "SQL Window Functions Tutorial", "Mode Analytics", "https://mode.com"),
                new Resource(steps.get(2), "VIDEO", "Python Pandas Crash Course", "YouTube", "https://youtube.com"),
                new Resource(steps.get(3), "PROJECT", "Executive Sales Performance Dashboard", "Power BI", "https://powerbi.microsoft.com")
        ));
    }

    private void seedDataEngineer() {
        Specialty spec = specialtyRepository.findByName("Data Engineer");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Data Engineering Roadmap", "Advanced", 14);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Data Modeling & Advanced SQL", "Star schema, snowflake schema, and query optimization.", 15),
                new LearningStep(path, 2, "Python Data Pipelines", "Automating ETL extraction, validation, and loading.", 20),
                new LearningStep(path, 3, "Big Data Processing with Spark", "PySpark, distributed computing, and streaming data.", 25),
                new LearningStep(path, 4, "Workflow Orchestration (Airflow)", "Scheduling DAGs, monitoring pipelines, and error handling.", 15)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "ARTICLE", "Data Warehouse Design Basics", "Towards Data Science", "https://towardsdatascience.com"),
                new Resource(steps.get(1), "COURSE", "Data Engineering with Python", "freeCodeCamp", "https://freecodecamp.org"),
                new Resource(steps.get(2), "DOCUMENTATION", "Apache Spark Quickstart", "Apache Spark", "https://spark.apache.org"),
                new Resource(steps.get(3), "VIDEO", "Apache Airflow Hands-on Tutorial", "YouTube", "https://youtube.com")
        ));
    }

    private void seedDevOps() {
        Specialty spec = specialtyRepository.findByName("Ingénieur Cloud/DevOps");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Cloud & DevOps Engineering Path", "Intermediate", 12);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Linux Administration & Bash Scripting", "Automate system management and script routines.", 15),
                new LearningStep(path, 2, "Containers with Docker", "Containerize Spring Boot and React applications.", 15),
                new LearningStep(path, 3, "CI/CD Pipelines", "Build automated testing and deployment pipelines with GitHub Actions.", 18),
                new LearningStep(path, 4, "Cloud Computing Basics (AWS / Azure)", "Deploy scalable compute, storage, and networking.", 20)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "COURSE", "Linux for DevOps", "YouTube", "https://youtube.com"),
                new Resource(steps.get(1), "DOCUMENTATION", "Docker Getting Started", "Docker Docs", "https://docs.docker.com"),
                new Resource(steps.get(2), "ARTICLE", "GitHub Actions CI/CD Complete Guide", "GitHub Docs", "https://docs.github.com"),
                new Resource(steps.get(3), "COURSE", "AWS Cloud Practitioner Essentials", "AWS Training", "https://aws.amazon.com")
        ));
    }

    private void seedQA() {
        Specialty spec = specialtyRepository.findByName("QA/Test Engineer");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "Software QA & Test Automation Path", "Beginner", 8);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "Software Testing Fundamentals", "Test plans, test cases, bug lifecycles, and ISTQB concepts.", 10),
                new LearningStep(path, 2, "API Testing with Postman", "Automate REST endpoint testing, collections, and assertions.", 12),
                new LearningStep(path, 3, "UI Test Automation with Selenium", "Web driver, locator strategies, and Page Object Model.", 18),
                new LearningStep(path, 4, "Continuous Testing & Reporting", "Run test suites in CI/CD and generate detailed reports.", 12)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "ARTICLE", "ISTQB Foundation Level Summary", "Guru99", "https://guru99.com"),
                new Resource(steps.get(1), "COURSE", "API Testing with Postman", "freeCodeCamp", "https://freecodecamp.org"),
                new Resource(steps.get(2), "VIDEO", "Selenium WebDriver Full Course", "YouTube", "https://youtube.com"),
                new Resource(steps.get(3), "PROJECT", "Automated Test Suite for ORIENTA+", "Internal", "https://github.com")
        ));
    }

    private void seedUIUX() {
        Specialty spec = specialtyRepository.findByName("UX/UI Designer");
        if (spec == null || !learningPathRepository.findBySpecialty(spec).isEmpty()) return;

        LearningPath path = new LearningPath(spec, "UI/UX Design Career Path", "Beginner", 8);
        learningPathRepository.save(path);

        List<LearningStep> steps = List.of(
                new LearningStep(path, 1, "UX Research & Persona Creation", "User interviews, empathy maps, and problem framing.", 12),
                new LearningStep(path, 2, "Wireframing & Prototyping in Figma", "Components, auto-layout, interactive prototypes, and design systems.", 20),
                new LearningStep(path, 3, "Visual Design & Accessibility", "Color theory, typography hierarchy, contrast, and WCAG standards.", 15),
                new LearningStep(path, 4, "Usability Testing & Design Handoff", "Test prototypes with users and export specs for developers.", 12)
        );
        learningStepRepository.saveAll(steps);

        resourceRepository.saveAll(List.of(
                new Resource(steps.get(0), "ARTICLE", "UX Design Process Overview", "Nielsen Norman Group", "https://www.nngroup.com"),
                new Resource(steps.get(1), "COURSE", "Figma for Beginners", "YouTube", "https://youtube.com"),
                new Resource(steps.get(2), "DOCUMENTATION", "Material Design Guidelines", "Material.io", "https://m3.material.io"),
                new Resource(steps.get(3), "PROJECT", "Complete Mobile & Web App Case Study", "Behance", "https://behance.net")
        ));
    }
}