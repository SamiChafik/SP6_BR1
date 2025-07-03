package com.example.enaa_skills.mappers;

import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.entities.SubCompetence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubCompetenceMapper{

    @Mapping(source = "competence_id", target = "competence.id")
    SubCompetence toEntity(SubCompetenceDTO dto);

    @Mapping(source = "competence.id", target = "competence_id")
    SubCompetenceDTO toDTO(SubCompetence subCompetence);

    List<SubCompetenceDTO> toDTOs(List<SubCompetence> subCompetenceList);
}
