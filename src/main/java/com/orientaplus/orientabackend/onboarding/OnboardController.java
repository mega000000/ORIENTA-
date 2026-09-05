package com.orientaplus.orientabackend.onboarding;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OnboardController {

    private final OnboardingService onboardingService;


public OnboardController(OnboardingService onboardingService){this.onboardingService=onboardingService;}

@PostMapping("/onboarding")
public ResponseEntity<?> onboarding(
        Authentication authentication,
        @Valid @RequestBody OnboardingRequest onboardingRequest
){
    long userId=Long.parseLong((String)authentication.getPrincipal());

    Onboarding onboarding=onboardingService.setOnboarding(onboardingRequest,userId);
    OnboardingResponse onboardingResponse=new OnboardingResponse(
            onboarding.getId(),
            onboarding.getStudyLevel(),
            onboarding.getWeeklyAvailableHours(),
            onboarding.getObjective()
    );
    return ResponseEntity.ok(onboardingResponse);
}

@GetMapping("/onboarding")
    public ResponseEntity<?> onboardingGet(Authentication authentication){


    long userId=Long.parseLong((String)authentication.getPrincipal());

   Onboarding onboarding=onboardingService.getOnboarding(userId);
   OnboardingResponse onboardingResponse=new OnboardingResponse(
           onboarding.getId(),
           onboarding.getStudyLevel(),
           onboarding.getWeeklyAvailableHours(),
           onboarding.getObjective()
   );
   return ResponseEntity.ok(onboardingResponse);
}
}
