package com.orientaplus.orientabackend.specialty;

import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.auth.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final UserRepository userRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository, UserRepository userRepository){
        this.specialtyRepository=specialtyRepository;
        this.userRepository=userRepository;
    }


    public List<SpecialtyResponse> getAllSpecialty(){

        List<Specialty> specialtyList=specialtyRepository.findAll();

        List<SpecialtyResponse> specialtyResponses = specialtyList.stream()
                .map(this::mapToResponse)
                .toList();

        return specialtyResponses;
    }

    public SpecialtyResponse getUserSpecialty(long id){

        Optional<User> optionalUser= userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("User Not Found");
        }
        User user=optionalUser.get();
        Specialty specialty =specialtyRepository.findByUser(user);
        if(specialty==null){
            throw new ResourceNotFoundException("Specialty Not Found");
        }

        return mapToResponse(specialty);
    }

    private SpecialtyResponse mapToResponse(Specialty specialty) {
        return new SpecialtyResponse(
                specialty.getId(),
                specialty.getName(),
                specialty.getDescription(),
                specialty.getMissions(),
                specialty.getTools(),
                specialty.getPrerequisites(),
                specialty.getOutlook()
        );
    }
    public List<SpecialtyResponse> searchSpecialties(String keyword){
        List<Specialty> results;

        if (keyword == null || keyword.isBlank()) {
            results = specialtyRepository.findAll();
        } else {
            results = specialtyRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        }

        return results.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SpecialtyResponse getSpecialtyById(long id){
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(id);
        if(optionalSpecialty.isEmpty()){
            throw new ResourceNotFoundException("Specialty Not Found");
        }
        return mapToResponse(optionalSpecialty.get());
    }
}