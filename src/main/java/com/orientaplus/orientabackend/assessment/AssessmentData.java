package com.orientaplus.orientabackend.assessment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssessmentData implements CommandLineRunner {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;

    public AssessmentData(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (questionnaireRepository.count() > 0) {
            return;
        }

        Questionnaire riasec = new Questionnaire("RIASEC", "RIASEC Questionnaire");
        questionnaireRepository.save(riasec);

        List<Question> questions = List.of(
                // R - Realistic
                new Question("I enjoy fixing or building things with my hands.", "R", 1, riasec),
                new Question("I prefer working with tools or machines.", "R", 2, riasec),
                new Question("I am comfortable installing or configuring technical equipment.", "R", 3, riasec),
                new Question("I like solving concrete, practical problems.", "R", 4, riasec),
                new Question("I prefer physical and hands-on activities over theoretical tasks.", "R", 5, riasec),

                // I - Investigative
                new Question("I enjoy analyzing data to understand a problem.", "I", 6, riasec),
                new Question("I am curious and like finding logical explanations.", "I", 7, riasec),
                new Question("I prefer thinking things through carefully before acting.", "I", 8, riasec),
                new Question("I like solving puzzles or complex problems.", "I", 9, riasec),
                new Question("I am interested in science and new technologies.", "I", 10, riasec),

                // A - Artistic
                new Question("I enjoy creating designs or visual interfaces.", "A", 11, riasec),
                new Question("I prefer tasks that leave room for imagination.", "A", 12, riasec),
                new Question("I like expressing ideas in original ways.", "A", 13, riasec),
                new Question("I am sensitive to aesthetics and visual style.", "A", 14, riasec),
                new Question("I like experimenting with new ways to present an idea.", "A", 15, riasec),

                // S - Social
                new Question("I enjoy helping others solve their problems.", "S", 16, riasec),
                new Question("I am comfortable explaining things to others.", "S", 17, riasec),
                new Question("I prefer working in a team rather than alone.", "S", 18, riasec),
                new Question("I like mentoring or training other people.", "S", 19, riasec),
                new Question("I am attentive to the needs of people around me.", "S", 20, riasec),

                // E - Enterprising
                new Question("I enjoy convincing others of an idea or project.", "E", 21, riasec),
                new Question("I easily take initiative.", "E", 22, riasec),
                new Question("I like organizing and leading projects.", "E", 23, riasec),
                new Question("I am motivated by challenges and competition.", "E", 24, riasec),
                new Question("I like negotiating or pitching an idea.", "E", 25, riasec),

                // C - Conventional
                new Question("I like following clear, well-defined procedures.", "C", 26, riasec),
                new Question("I prefer organized and structured work.", "C", 27, riasec),
                new Question("I enjoy checking and correcting details in a document.", "C", 28, riasec),
                new Question("I am comfortable with repetitive and precise tasks.", "C", 29, riasec),
                new Question("I like managing data or files in a methodical way.", "C", 30, riasec)
        );

        questionRepository.saveAll(questions);
    }
}