package com.example.enaa_skills.mappers;

import com.example.enaa_skills.dtos.CompetenceDTO;
import com.example.enaa_skills.entities.Competence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetenceMapper{
    Competence toEntity(CompetenceDTO dto);
    CompetenceDTO toDTO(Competence competence);
    List<CompetenceDTO> toDTOs(List<Competence> competenceList);
}
