package com.orientaplus.orientabackend.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire,Long> {

    Questionnaire findByType(String type);
}
