package com.orientaplus.orientabackend.onboarding;

import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.auth.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class OnboardingService {

    private final UserRepository userRepository;
    private final OnboardingRepository onboardingRepository;

    public OnboardingService(UserRepository userRepository,OnboardingRepository onboardingRepository){

        this.userRepository=userRepository;
        this.onboardingRepository=onboardingRepository;
    }
    public Onboarding setOnboarding(OnboardingRequest request, long userId) {

        Optional<User> optionalUser= userRepository.findById(userId);
        if(optionalUser.isEmpty()){throw new ResourceNotFoundException("User Not Found");}
        User user=optionalUser.get();
        Onboarding existingOnboarding= onboardingRepository.findByUser(user);
        if(existingOnboarding!=null){
            throw new ResourceNotFoundException("User already have an Onboarding");
        }
        Onboarding onboarding=new Onboarding(
                request.getStudyLevel(),
                request.getWeeklyAvailableHours(),
                request.getObjective(),
                user
        );
        return onboardingRepository.save(onboarding);
    }

    public Onboarding getOnboarding(long userId){


        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){throw new ResourceNotFoundException("User Not Found");}
        User user = optionalUser.get();
        Onboarding onboarding=onboardingRepository.findByUser(user);
        if(onboarding==null){throw new ResourceNotFoundException("Onboarding Not Found");}
        return onboarding;
    }
}
