package com.orientaplus.orientabackend.gamification;

import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.auth.UserRepository;
import com.orientaplus.orientabackend.learningpath.LearningPath;
import com.orientaplus.orientabackend.learningpath.LearningPathRepository;
import com.orientaplus.orientabackend.learningpath.LearningStep;
import com.orientaplus.orientabackend.learningpath.LearningStepRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class ProgressService {

    private final UserRepository userRepository;
    private final LearningPathRepository learningPathRepository;
    private final LearningStepRepository learningStepRepository;
    private final UserPathRepository userPathRepository;
    private final StepProgressRepository stepProgressRepository;
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

    public ProgressService(UserRepository userRepository,
                           LearningPathRepository learningPathRepository,
                           LearningStepRepository learningStepRepository,
                           UserPathRepository userPathRepository,
                           StepProgressRepository stepProgressRepository,
                           BadgeRepository badgeRepository,
                           UserBadgeRepository userBadgeRepository){
        this.userRepository = userRepository;
        this.learningPathRepository = learningPathRepository;
        this.learningStepRepository = learningStepRepository;
        this.userPathRepository = userPathRepository;
        this.stepProgressRepository = stepProgressRepository;
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
    }

    public UserPathResponse startPath(long userId, long pathId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){ throw new ResourceNotFoundException("User Not Found"); }
        User user = optionalUser.get();

        Optional<LearningPath> optionalPath = learningPathRepository.findById(pathId);
        if(optionalPath.isEmpty()){ throw new ResourceNotFoundException("Learning Path Not Found"); }
        LearningPath path = optionalPath.get();

        UserPath userPath = new UserPath(user, path);
        userPathRepository.save(userPath);

        List<LearningStep> steps = learningStepRepository.findByPathOrderByOrderIndexAsc(path);
        List<StepProgress> progressList = steps.stream()
                .map(step -> new StepProgress(userPath, step))
                .toList();
        stepProgressRepository.saveAll(progressList);

        awardBadge(user, "PATH_STARTED");

        return mapToResponse(userPath);
    }

    public UserPathResponse completeStep(long userPathId, long stepId){
        Optional<UserPath> optionalUserPath = userPathRepository.findById(userPathId);
        if(optionalUserPath.isEmpty()){ throw new ResourceNotFoundException("User Path Not Found"); }
        UserPath userPath = optionalUserPath.get();

        List<StepProgress> allProgress = stepProgressRepository.findByUserPath(userPath);

        StepProgress targetProgress = allProgress.stream()
                .filter(p -> p.getStep().getId() == stepId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Step Not Found In This Path"));

        targetProgress.setStatus("COMPLETED");
        targetProgress.setCompletedAt(LocalDateTime.now());
        stepProgressRepository.save(targetProgress);

        long completedCount = allProgress.stream().filter(p -> p.getStatus().equals("COMPLETED")).count();
        double newProgress = ((double) completedCount / allProgress.size()) * 100;
        userPath.setProgress(newProgress);

        if(newProgress >= 100){
            userPath.setStatus("COMPLETED");
            awardBadge(userPath.getUser(), "PATH_COMPLETED");
        }

        userPathRepository.save(userPath);

        return mapToResponse(userPath);
    }

    private void awardBadge(User user, String ruleCode){
        Badge badge = badgeRepository.findByRuleCode(ruleCode);
        if(badge == null) return;

        if(userBadgeRepository.existsByUserAndBadge(user, badge)) return;

        userBadgeRepository.save(new UserBadge(user, badge));

        user.setXp(user.getXp() + badge.getXpReward());
        userRepository.save(user);
    }

    private UserPathResponse mapToResponse(UserPath userPath){
        return new UserPathResponse(
                userPath.getId(),
                userPath.getPath().getTitle(),
                userPath.getStatus(),
                userPath.getProgress()
        );
    }
    public GamificationResponse getMyGamification(long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){ throw new ResourceNotFoundException("User Not Found"); }
        User user = optionalUser.get();

        List<UserBadge> userBadges = userBadgeRepository.findByUser(user);
        List<String> badgeNames = userBadges.stream()
                .map(ub -> ub.getBadge().getName())
                .toList();

        return new GamificationResponse(user.getXp(), badgeNames);
    }
}