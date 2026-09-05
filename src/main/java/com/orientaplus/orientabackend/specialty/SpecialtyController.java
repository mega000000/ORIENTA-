package com.orientaplus.orientabackend.specialty;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController (SpecialtyService specialtyService){
        this.specialtyService=specialtyService;
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<SpecialtyResponse>> getSpecialties(){

        List<SpecialtyResponse> specialtyResponse = specialtyService.getAllSpecialty();

        return ResponseEntity.ok(specialtyResponse);
    }

    @GetMapping("/user/specialty")
    public ResponseEntity<SpecialtyResponse> getUserSpecialty(Authentication authentication){

        long id=Long.parseLong((String)authentication.getPrincipal());

        SpecialtyResponse specialtyResponse=specialtyService.getUserSpecialty(id);

     return ResponseEntity.ok(specialtyResponse);
    }
    @GetMapping("/specialties/search")
    public ResponseEntity<List<SpecialtyResponse>> searchSpecialties(@RequestParam(required = false) String keyword){
        return ResponseEntity.ok(specialtyService.searchSpecialties(keyword));
    }

    @GetMapping("/specialties/{id}")
    public ResponseEntity<SpecialtyResponse> getSpecialtyDetails(@PathVariable long id){
        return ResponseEntity.ok(specialtyService.getSpecialtyById(id));
    }
}
