package com.orientaplus.orientabackend.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByQuestionnaire(Questionnaire questionnaire);

}
