package com.orientaplus.orientabackend.learningpath;

import com.orientaplus.orientabackend.specialty.Specialty;
import com.orientaplus.orientabackend.specialty.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class LearningPathService {

    private final SpecialtyRepository specialtyRepository;
    private final LearningPathRepository learningPathRepository;
    private final LearningStepRepository learningStepRepository;
    private final ResourceRepository resourceRepository;

    public LearningPathService(SpecialtyRepository specialtyRepository,
                               LearningPathRepository learningPathRepository,
                               LearningStepRepository learningStepRepository,
                               ResourceRepository resourceRepository){
        this.specialtyRepository = specialtyRepository;
        this.learningPathRepository = learningPathRepository;
        this.learningStepRepository = learningStepRepository;
        this.resourceRepository = resourceRepository;
    }

    public List<LearningPathResponse> getPathsBySpecialty(long specialtyId){
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);
        if(optionalSpecialty.isEmpty()){ throw new ResourceNotFoundException("Specialty Not Found"); }
        Specialty specialty = optionalSpecialty.get();

        List<LearningPath> paths = learningPathRepository.findBySpecialty(specialty);

        return paths.stream().map(this::mapToResponse).toList();
    }

    public LearningPathResponse getPathById(long pathId){
        Optional<LearningPath> optionalPath = learningPathRepository.findById(pathId);
        if(optionalPath.isEmpty()){ throw new ResourceNotFoundException("Learning Path Not Found"); }

        return mapToResponse(optionalPath.get());
    }

    private LearningPathResponse mapToResponse(LearningPath path){
        List<LearningStep> steps = learningStepRepository.findByPathOrderByOrderIndexAsc(path);

        List<LearningStepResponse> stepResponses = steps.stream()
                .map(this::mapStepToResponse)
                .toList();

        return new LearningPathResponse(
                path.getId(),
                path.getTitle(),
                path.getLevel(),
                path.getEstimatedWeeks(),
                stepResponses
        );
    }

    private LearningStepResponse mapStepToResponse(LearningStep step){
        List<Resource> resources = resourceRepository.findByStep(step);

        List<ResourceResponse> resourceResponses = resources.stream()
                .map(r -> new ResourceResponse(r.getId(), r.getType(), r.getTitle(), r.getProvider(), r.getUrl()))
                .toList();

        return new LearningStepResponse(
                step.getId(),
                step.getOrderIndex(),
                step.getTitle(),
                step.getObjective(),
                step.getDurationHours(),
                resourceResponses
        );
    }
}