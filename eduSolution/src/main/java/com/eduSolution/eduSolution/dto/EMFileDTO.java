package com.eduSolution.eduSolution.dto;

import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.EduMaterial;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class EMFileDTO {

    private int id;

    private String name;

    private String type;

    private byte[] fileData;

    private Set<EduMaterial> eduMaterialIds;

    public EMFileDTO (EMFile emFile) {
        setId(emFile.getId());
        setName(emFile.getName());
        setType(emFile.getType());
        setFileData(emFile.getFileData());
        setEduMaterialIds((Set<EduMaterial>) emFile.getEduMaterial());
    }
}
