package com.eduSolution.eduSolution.dto;

import com.eduSolution.eduSolution.entity.EduMaterial;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EduMaterialDTO {

    private int id;

    private String name;

    private String description;

    public EduMaterialDTO (EduMaterial eduMaterial) {
        setId(eduMaterial.getId());
        setName(eduMaterial.getName());
        setDescription(eduMaterial.getDescription());
    }
}
