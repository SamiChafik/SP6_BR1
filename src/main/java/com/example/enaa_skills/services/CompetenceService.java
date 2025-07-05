package com.example.enaa_skills.services;

import com.example.enaa_skills.dtos.CompetenceDTO;
import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.entities.Competence;
import com.example.enaa_skills.entities.SubCompetence;
import com.example.enaa_skills.mappers.CompetenceMapper;
import com.example.enaa_skills.mappers.SubCompetenceMapper;
import com.example.enaa_skills.repositories.CompetenceRepository;
import com.example.enaa_skills.repositories.SubCompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceMapper mapper;
    private final CompetenceRepository repository;
    private final SubCompetenceRepository subCompetenceRepository;
    private final SubCompetenceMapper subCompetenceMapper;


    public CompetenceService(CompetenceMapper mapper, CompetenceRepository repository, SubCompetenceRepository subCompetenceRepository, SubCompetenceMapper subCompetenceMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.subCompetenceRepository = subCompetenceRepository;
        this.subCompetenceMapper = subCompetenceMapper;
    }

    public CompetenceDTO addCompetence(CompetenceDTO dto){
        Competence competence = mapper.toEntity(dto);
        Competence savedCompetence = repository.save(competence);
        return mapper.toDTO(savedCompetence);
//        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public CompetenceDTO updateCompetence(Long id, CompetenceDTO dto){
        Competence competence = repository.findById(id)
                .orElseThrow(()->new RuntimeException("competence not found"));
        competence.setName(dto.getName());
        competence.setDescription(dto.getDescription());
//        CompetenceDTO competenceDTO = mapper.toDTO(repository.save(competence));
//        return competenceDTO;
        return mapper.toDTO(repository.save(competence));
    }

    public List<CompetenceDTO> getAll(){
        List<Competence> competenceList = repository.findAll();

        CompetenceDTO competenceDTO;
        return mapper.toDTOs(competenceList);
    }

    public List<Competence> getAll2(){
        List<Competence> competenceList = repository.findAll();

        return competenceList;
    }

    public List<SubCompetenceDTO> getByCompetenceById(Long id){
        List<SubCompetence> subCompetenceList = subCompetenceRepository.findAllByCompetence_Id(id);
        List<SubCompetenceDTO> subCompetenceDTOList = subCompetenceMapper.toDTOs(subCompetenceList);
        return subCompetenceDTOList;
    }

    public void deleteCompetence(Long id){
        repository.deleteById(id);
    }

//    public boolean isAcquired(Long competenceId) {
//        List<SubCompetence> subCompetenceList = subCompetenceRepository.findAllByCompetence_Id(competenceId);
//        return subCompetenceList != null &&
//                !subCompetenceList.isEmpty() &&
//                subCompetenceList.stream().allMatch(SubCompetence::isValidated);
//    }
}
