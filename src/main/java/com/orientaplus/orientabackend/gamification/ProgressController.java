package com.orientaplus.orientabackend.gamification;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService){
        this.progressService = progressService;
    }

    @PostMapping("/user-paths/{pathId}")
    public ResponseEntity<UserPathResponse> startPath(@PathVariable long pathId, Authentication authentication){
        long userId = Long.parseLong((String) authentication.getPrincipal());
        return ResponseEntity.ok(progressService.startPath(userId, pathId));
    }

    @PatchMapping("/user-paths/{userPathId}/steps/{stepId}")
    public ResponseEntity<UserPathResponse> completeStep(@PathVariable long userPathId, @PathVariable long stepId){
        return ResponseEntity.ok(progressService.completeStep(userPathId, stepId));
    }
    @GetMapping("/gamification/me")
    public ResponseEntity<GamificationResponse> getMyGamification(Authentication authentication){
        long userId = Long.parseLong((String) authentication.getPrincipal());
        return ResponseEntity.ok(progressService.getMyGamification(userId));
    }
}