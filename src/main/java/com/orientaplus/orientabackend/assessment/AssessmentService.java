package com.orientaplus.orientabackend.assessment;

import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.auth.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class AssessmentService {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final AssessmentSessionRepository sessionRepository;
    private final AnswerRepository answerRepository;
    private final AssessmentScoreRepository scoreRepository;
    private final UserRepository userRepository;

    public AssessmentService(QuestionnaireRepository questionnaireRepository,
                             QuestionRepository questionRepository,
                             AssessmentSessionRepository sessionRepository,
                             AnswerRepository answerRepository,
                             AssessmentScoreRepository scoreRepository,
                             UserRepository userRepository){
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.sessionRepository = sessionRepository;
        this.answerRepository = answerRepository;
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    public QuestionnaireResponse getQuestionnaireByType(String type){
        Questionnaire questionnaire = questionnaireRepository.findByType(type);
        if(questionnaire == null){ throw new ResourceNotFoundException("Questionnaire Not Found"); }

        List<Question> questions = questionRepository.findByQuestionnaire(questionnaire);

        List<QuestionResponse> questionResponses = questions.stream()
                .map(q -> new QuestionResponse(q.getId(), q.getText(), q.getOrderIndex()))
                .toList();

        return new QuestionnaireResponse(questionnaire.getId(), questionnaire.getTitle(), questionResponses);
    }

    public SessionResponse startSession(long userId, String type){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){ throw new ResourceNotFoundException("User Not Found"); }
        User user = optionalUser.get();

        Questionnaire questionnaire = questionnaireRepository.findByType(type);
        if(questionnaire == null){ throw new ResourceNotFoundException("Questionnaire Not Found"); }

        AssessmentSession session = new AssessmentSession(user, questionnaire);
        sessionRepository.save(session);

        return new SessionResponse(session.getId(), session.getStatus());
    }

    public AssessmentResultResponse submitAnswers(long sessionId, List<AnswerRequest> answerRequests){
        Optional<AssessmentSession> optionalSession = sessionRepository.findById(sessionId);
        if(optionalSession.isEmpty()){ throw new ResourceNotFoundException("Session Not Found"); }
        AssessmentSession session = optionalSession.get();

        List<Answer> answers = new ArrayList<>();

        for(AnswerRequest req : answerRequests){
            Optional<Question> optionalQuestion = questionRepository.findById(req.getQuestionId());
            if(optionalQuestion.isEmpty()){ throw new ResourceNotFoundException("Question Not Found: " + req.getQuestionId()); }
            Question question = optionalQuestion.get();

            Answer answer = new Answer(session, question, req.getValue());
            answers.add(answer);
        }

        answerRepository.saveAll(answers);

        session.setStatus("COMPLETED");
        session.setCompletedAt(LocalDateTime.now());
        sessionRepository.save(session);

        Map<String, Double> scores = calculateScores(answers);

        List<AssessmentScore> assessmentScores = scores.entrySet().stream()
                .map(entry -> new AssessmentScore(session, entry.getKey(), entry.getValue()))
                .toList();
        scoreRepository.saveAll(assessmentScores);

        String dominantCode = getDominantCode(scores);

        return new AssessmentResultResponse(session.getId(), scores, dominantCode);
    }

    private Map<String, Double> calculateScores(List<Answer> answers){
        Map<String, List<Integer>> valuesByDimension = new HashMap<>();

        for(Answer answer : answers){
            String dimension = answer.getQuestion().getDimension();
            valuesByDimension.computeIfAbsent(dimension, k -> new ArrayList<>()).add(answer.getValue());
        }

        Map<String, Double> scores = new HashMap<>();
        for(Map.Entry<String, List<Integer>> entry : valuesByDimension.entrySet()){
            int sum = entry.getValue().stream().mapToInt(Integer::intValue).sum();
            int maxPossible = entry.getValue().size() * 5;
            double normalized = ((double) sum / maxPossible) * 100;
            scores.put(entry.getKey(), normalized);
        }

        return scores;
    }

    private String getDominantCode(Map<String, Double> scores){
        return scores.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(2)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining());
    }

    public AssessmentResultResponse getResult(long sessionId){
        Optional<AssessmentSession> optionalSession = sessionRepository.findById(sessionId);
        if(optionalSession.isEmpty()){ throw new ResourceNotFoundException("Session Not Found"); }
        AssessmentSession session = optionalSession.get();

        List<AssessmentScore> assessmentScores = scoreRepository.findBySession(session);
        Map<String, Double> scores = new HashMap<>();
        for(AssessmentScore s : assessmentScores){
            scores.put(s.getDimension(), s.getScore());
        }

        String dominantCode = getDominantCode(scores);

        return new AssessmentResultResponse(session.getId(), scores, dominantCode);
    }
}