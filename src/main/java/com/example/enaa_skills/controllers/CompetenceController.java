package com.example.enaa_skills.controllers;

import com.example.enaa_skills.dtos.CompetenceDTO;
import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.services.CompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competence")
public class CompetenceController {
    private final CompetenceService service;

    public CompetenceController(CompetenceService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<CompetenceDTO> getCompetenceList(){
        return service.getAll();
    }

    @GetMapping("/getSubs/{id}")
    public List<SubCompetenceDTO> getCompetenceById(@PathVariable Long id){
        return service.getByCompetenceById(id);
    }

    @PostMapping("/add")
    public CompetenceDTO addCompetence(@RequestBody CompetenceDTO dto){
        return service.addCompetence(dto);
    }

    @PutMapping("/update/{id}")
    public CompetenceDTO updateCompetence(@PathVariable Long id, @RequestBody CompetenceDTO dto){
        return service.updateCompetence(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompetence(@PathVariable Long id){
        service.deleteCompetence(id);
    }
}
