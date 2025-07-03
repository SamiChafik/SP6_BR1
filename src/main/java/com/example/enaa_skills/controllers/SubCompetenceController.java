package com.example.enaa_skills.controllers;

import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.services.SubCompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subCompetence")
public class SubCompetenceController {
    private final SubCompetenceService service;

    public SubCompetenceController(SubCompetenceService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<SubCompetenceDTO> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public SubCompetenceDTO addSubCompetence(@RequestBody SubCompetenceDTO dto){
        return service.addSubCompetence(dto);
    }

    @PutMapping("/update/{id}")
    public SubCompetenceDTO updateSubCompetence(@PathVariable Long id, @RequestBody SubCompetenceDTO dto){
        return service.updateSubCompetence(id, dto);
    }

    @PatchMapping("/validate/{id}")
    public SubCompetenceDTO validateSubCompetence(
            @PathVariable Long id,
            @RequestParam boolean isValid){
        return service.validate(id, isValid);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubCompetence(@PathVariable Long id){
        service.deleteSubCompetence(id);
    }
}
