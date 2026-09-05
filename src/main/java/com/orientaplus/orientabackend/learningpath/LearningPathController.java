package com.orientaplus.orientabackend.learningpath;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LearningPathController {

    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService){
        this.learningPathService = learningPathService;
    }

    @GetMapping("/specialties/{specialtyId}/learning-paths")
    public ResponseEntity<List<LearningPathResponse>> getPathsBySpecialty(@PathVariable long specialtyId){
        return ResponseEntity.ok(learningPathService.getPathsBySpecialty(specialtyId));
    }

    @GetMapping("/learning-paths/{pathId}")
    public ResponseEntity<LearningPathResponse> getPathById(@PathVariable long pathId){
        return ResponseEntity.ok(learningPathService.getPathById(pathId));
    }
}