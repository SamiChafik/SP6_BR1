package com.example.enaa_skills.services;

import com.example.enaa_skills.dtos.SubCompetenceDTO;
import com.example.enaa_skills.entities.Competence;
import com.example.enaa_skills.entities.SubCompetence;
import com.example.enaa_skills.mappers.SubCompetenceMapper;
import com.example.enaa_skills.repositories.CompetenceRepository;
import com.example.enaa_skills.repositories.SubCompetenceRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class SubCompetenceServiceTest {
    SubCompetenceMapper mapper = mock(SubCompetenceMapper.class);

    SubCompetenceRepository repository = mock(SubCompetenceRepository.class);

    CompetenceRepository competenceRepository = mock(CompetenceRepository.class);

    CompetenceService competenceService = mock(CompetenceService.class);

    SubCompetenceService service = new SubCompetenceService(mapper, repository, competenceRepository, competenceService);

    @Test
    void addSubCompetenceTest() {
        SubCompetenceDTO dto = new SubCompetenceDTO();
        dto.setName("C");
        dto.setDescription("TEST");
        dto.setCompetence_id(1L);

        SubCompetence entity = new SubCompetence();
        entity.setName("C");
        entity.setDescription("TEST");

        Competence competence = new Competence();
        competence.setId(1L);

        SubCompetence saved = new SubCompetence();
        saved.setId(1L);
        saved.setName("C");
        saved.setDescription("TEST");
        saved.setCompetence(competence);

        SubCompetenceDTO resultDto = new SubCompetenceDTO();
        resultDto.setName("C");
        resultDto.setDescription("TEST");

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(resultDto);

        SubCompetenceDTO result = service.addSubCompetence(dto);

        assertEquals("C", result.getName());
        verify(repository).save(entity);
    }

    @Test
    void updateSubCompetence() {
        Long id = 1L;
        SubCompetenceDTO inputDTO = new SubCompetenceDTO();
        inputDTO.setName("C2");

        SubCompetence existingEntity = new SubCompetence();
        existingEntity.setId(id);
        existingEntity.setName("name");

        SubCompetence updatedEntity = new SubCompetence();
        updatedEntity.setId(id);
        updatedEntity.setName("C2");

        SubCompetenceDTO returnedDTO = new SubCompetenceDTO();
        returnedDTO.setName("C2");

        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(repository.save(existingEntity)).thenReturn(updatedEntity);
        when(mapper.toDTO(updatedEntity)).thenReturn(returnedDTO);

        SubCompetenceDTO result = service.updateSubCompetence(id, inputDTO);

        assertEquals("C2", result.getName());
    }

    @Test
    void getAllSubCompetenceListTest() {
        SubCompetence s1 = new SubCompetence();
        s1.setName("A");
        SubCompetence s2 = new SubCompetence();
        s2.setName("B");

        List<SubCompetence> list = List.of(s1, s2);

        SubCompetenceDTO dto1 = new SubCompetenceDTO();
        dto1.setName("A");
        SubCompetenceDTO dto2 = new SubCompetenceDTO();
        dto2.setName("B");

        when(repository.findAll()).thenReturn(list);
        when(mapper.toDTOs(list)).thenReturn(List.of(dto1, dto2));

        List<SubCompetenceDTO> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());
        assertEquals("B", result.get(1).getName());
    }

    @Test
    void deleteSubCompetenceTest() {
        Long id = 1L;

        service.deleteSubCompetence(id);

        verify(repository).deleteById(id);
    }
}