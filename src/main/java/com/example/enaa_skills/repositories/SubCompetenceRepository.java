package com.example.enaa_skills.repositories;

import com.example.enaa_skills.entities.SubCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCompetenceRepository extends JpaRepository<SubCompetence, Long> {
    List<SubCompetence> findAllByCompetence_Id(Long id);

}
