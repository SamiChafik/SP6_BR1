package com.example.enaa_skills.services;

import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.entities.Competence;
import com.example.enaa_skills.entities.SubCompetence;
import com.example.enaa_skills.mappers.SubCompetenceMapper;
import com.example.enaa_skills.repositories.CompetenceRepository;
import com.example.enaa_skills.repositories.SubCompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCompetenceService {
    private final SubCompetenceMapper mapper;
    private final SubCompetenceRepository repository;
    private final CompetenceRepository competenceRepository;
    private final CompetenceService competenceService;

    public SubCompetenceService(
            SubCompetenceMapper mapper,
            SubCompetenceRepository repository,
            CompetenceRepository competenceRepository,
            CompetenceService competenceService) {
        this.mapper = mapper;
        this.repository = repository;
        this.competenceRepository = competenceRepository;
        this.competenceService = competenceService;
    }

    public SubCompetenceDTO addSubCompetence(SubCompetenceDTO dto){
        SubCompetence subCompetence = mapper.toEntity(dto);
        Competence competence = competenceRepository.findById(dto.getCompetence_id())
                .orElseThrow(()->new RuntimeException("competence not found"));
        subCompetence.setCompetence(competence);
        subCompetence.setValidated(false);
        SubCompetence saved = repository.save(subCompetence);

        checkAndUpdateCompetenceStatus(competence.getId());

        return mapper.toDTO(saved);
    }

    public SubCompetenceDTO updateSubCompetence(Long id, SubCompetenceDTO dto){
        SubCompetence subCompetence = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("subCompetence not found"));
        subCompetence.setName(dto.getName());
//        subCompetence.setValidated(dto.isValidated());
        subCompetence.setDescription(dto.getDescription());
        return mapper.toDTO(repository.save(subCompetence));
    }

    public SubCompetenceDTO validate(Long id, boolean isValidated){
        SubCompetence subCompetence = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("subCompetence not found"));
        subCompetence.setValidated(isValidated);
        SubCompetence saved = repository.save(subCompetence);

        checkAndUpdateCompetenceStatus(saved.getCompetence().getId());

        return mapper.toDTO(saved);
    }

    public List<SubCompetenceDTO> getAll(){
        List<SubCompetence> subCompetenceList = repository.findAll();
        List<SubCompetenceDTO> subCompetenceDTOList = mapper.toDTOs(subCompetenceList);
        return subCompetenceDTOList;
    }

    public void deleteSubCompetence(Long id){
        SubCompetence subCompetence = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("subCompetence not found"));
        Long competenceId = subCompetence.getCompetence().getId();
        repository.deleteById(id);

        checkAndUpdateCompetenceStatus(competenceId);
    }

    private void checkAndUpdateCompetenceStatus(Long competenceId) {
        boolean allValidated = competenceService.isAcquired(competenceId);

        Competence competence = competenceRepository.findById(competenceId)
                .orElseThrow(() -> new RuntimeException("Competence not found"));
        competence.setValidated(allValidated);
        competenceRepository.save(competence);
    }

}
