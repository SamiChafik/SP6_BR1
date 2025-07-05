package com.example.enaa_skills.controllers;

import com.example.enaa_skills.dtos.CompetenceDTO;
import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.entities.Competence;
import com.example.enaa_skills.services.CompetenceService;
import com.example.enaa_skills.services.ExceleServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/competence")
public class CompetenceController {
    private final CompetenceService service;
    private final ExceleServices excel;

    public CompetenceController(CompetenceService service, ExceleServices excel) {
        this.service = service;
        this.excel = excel;
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

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // 1. Définir le type de contenu de la réponse
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 2. Créer un nom de fichier dynamique
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=rapportcompetences" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // 3. Récupérer les données à exporter
        List<Competence> competences = service.getAll2();

        // 4. Appeler le service d'export
        excel.exportCompetencesToExcel(competences, response.getOutputStream());
    }
}
