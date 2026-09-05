package com.orientaplus.orientabackend.assessment;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/questionnaire/{type}")
    public ResponseEntity<QuestionnaireResponse> getQuestionnaire(@PathVariable String type){
        return ResponseEntity.ok(assessmentService.getQuestionnaireByType(type));
    }

    @PostMapping("/assessments/start/{type}")
    public ResponseEntity<SessionResponse> startSession(@PathVariable String type, Authentication authentication){
        long userId = Long.parseLong((String) authentication.getPrincipal());
        return ResponseEntity.ok(assessmentService.startSession(userId, type));
    }

    @PostMapping("/assessments/{sessionId}/submit")
    public ResponseEntity<AssessmentResultResponse> submitAnswers(@PathVariable long sessionId, @Valid @RequestBody List<AnswerRequest> answers){
        return ResponseEntity.ok(assessmentService.submitAnswers(sessionId, answers));
    }

    @GetMapping("/assessments/{sessionId}/result")
    public ResponseEntity<AssessmentResultResponse> getResult(@PathVariable long sessionId){
        return ResponseEntity.ok(assessmentService.getResult(sessionId));
    }
}