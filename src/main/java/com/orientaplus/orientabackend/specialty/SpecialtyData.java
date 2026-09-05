package com.orientaplus.orientabackend.specialty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class SpecialtyData implements CommandLineRunner {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyData(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (specialtyRepository.count() > 0) {
            return;
        }

        List<Specialty> specialties = List.of(
                new Specialty("Développeur Frontend",
                        "Conçoit et développe l'interface utilisateur des applications web.",
                        "Build responsive UIs, integrate APIs, optimize performance and accessibility.",
                        "React, TypeScript, HTML/CSS, Vite, Figma",
                        "Basic programming logic, HTML/CSS fundamentals",
                        "Frontend Developer, UI Engineer roles in tech companies and startups"),

                new Specialty("Développeur Backend Java",
                        "Développe la logique métier et les API REST avec Java et Spring Boot.",
                        "Design REST APIs, manage databases, implement business logic and security.",
                        "Java, Spring Boot, PostgreSQL, JPA/Hibernate, Docker",
                        "Object-oriented programming, basic SQL knowledge",
                        "Backend Developer, Java Engineer positions across industries"),

                new Specialty("Développeur Full Stack",
                        "Maîtrise à la fois le frontend et le backend pour construire des applications complètes.",
                        "Build end-to-end features, coordinate frontend/backend, deploy applications.",
                        "React, Spring Boot, PostgreSQL, Docker, Git",
                        "Programming fundamentals, understanding of web architecture",
                        "Full Stack Developer roles, startup and enterprise positions"),

                new Specialty("Développeur Mobile",
                        "Crée des applications mobiles natives ou hybrides pour Android et iOS.",
                        "Design mobile UIs, integrate device features, optimize app performance.",
                        "Flutter, React Native, Kotlin, Swift",
                        "Programming fundamentals, mobile UI/UX basics",
                        "Mobile Developer roles in app-focused companies"),

                new Specialty("Data Analyst",
                        "Analyse des données pour en extraire des insights et aider à la prise de décision.",
                        "Clean data, build dashboards, extract insights, present findings to stakeholders.",
                        "SQL, Python, Excel, Power BI/Tableau",
                        "Basic statistics, spreadsheet skills",
                        "Data Analyst positions across finance, marketing, and tech sectors"),

                new Specialty("Data Engineer",
                        "Conçoit et maintient les pipelines de données et les infrastructures de traitement.",
                        "Build data pipelines, manage data warehouses, ensure data quality.",
                        "Python, SQL, Apache Spark, Airflow, Cloud platforms",
                        "Programming fundamentals, database knowledge",
                        "Data Engineer roles in data-driven organizations"),

                new Specialty("Analyste Cybersécurité",
                        "Protège les systèmes d'information contre les menaces et vulnérabilités.",
                        "Monitor security systems, respond to incidents, perform risk assessments.",
                        "SIEM tools, Wireshark, Linux, networking fundamentals",
                        "Networking basics, security fundamentals",
                        "Security Analyst, SOC Analyst positions in enterprises"),

                new Specialty("Ingénieur Cloud/DevOps",
                        "Automatise le déploiement et gère l'infrastructure cloud des applications.",
                        "Automate deployments, manage cloud infrastructure, ensure system reliability.",
                        "Docker, Kubernetes, AWS/Azure, CI/CD tools, Terraform",
                        "Linux fundamentals, basic networking, scripting",
                        "DevOps Engineer, Cloud Engineer roles in modern tech companies"),

                new Specialty("QA/Test Engineer",
                        "Assure la qualité logicielle à travers des tests manuels et automatisés.",
                        "Write test plans, automate test suites, report and track bugs.",
                        "Selenium, JUnit, Postman, Jira",
                        "Basic programming, attention to detail",
                        "QA Engineer, Test Automation Engineer roles"),

                new Specialty("UX/UI Designer",
                        "Conçoit des expériences et interfaces utilisateur intuitives et esthétiques.",
                        "Design wireframes and prototypes, conduct user research, iterate on feedback.",
                        "Figma, Adobe XD, user research methods",
                        "Design fundamentals, visual communication skills",
                        "UX/UI Designer, Product Designer roles")
        );

        specialtyRepository.saveAll(specialties);
    }
}