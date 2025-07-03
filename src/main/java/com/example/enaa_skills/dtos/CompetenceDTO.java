package com.example.enaa_skills.dtos;

import com.example.enaa_skills.entities.SubCompetence;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDTO {
    private String name;
    private String description;
    private boolean validated;
//    private List<SubCompetence> competenceList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    //    public List<SubCompetence> getCompetenceList() {
//        return competenceList;
//    }
//
//    public void setCompetenceList(List<SubCompetence> competenceList) {
//        this.competenceList = competenceList;
//    }
}
